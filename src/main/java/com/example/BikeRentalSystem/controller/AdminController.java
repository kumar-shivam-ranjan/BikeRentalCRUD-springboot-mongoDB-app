package com.example.BikeRentalSystem.controller;

import javax.servlet.http.HttpSession;
import com.example.BikeRentalSystem.RestEndpoints.*;
import com.example.BikeRentalSystem.entities.*;
import com.example.BikeRentalSystem.service.SequenceGeneratoryService;
import io.swagger.models.auth.In;
import org.apache.catalina.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.BikeRentalSystem.messages.Messages;
import com.example.BikeRentalSystem.repository.UserRepository;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.annotations.ApiIgnore;
import java.util.*;


@ApiIgnore
@Controller
public class AdminController {

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private SequenceGeneratoryService sequenceGeneratoryService;
	private static final Logger logger= LogManager.getLogger(AdminController.class);



	@RequestMapping("/adminlogin")
	public String adminlogin(HttpSession session) {
		Optional<Object> admin=Optional.ofNullable(session.getAttribute("admin"));
		if(admin.isPresent()){
			return "redirect:/admin-dashboard";
		} else{
			return "adminlogin";
		}
	}
	
	
	
	@RequestMapping("/process_admin_login")
	public String processAdminLogin(@RequestParam("admin_email") String email, @RequestParam("admin_password") String password,HttpSession session,Model model) {
		Optional<Object> admin=Optional.ofNullable(session.getAttribute("admin"));
		if(admin.isPresent()) {
			return "redirect:/admin-dashboard";
		}
		try {
			Map<String,String> params =new HashMap<>();
			params.put("email",email);
			Users adminUser=restTemplate.getForObject(UserEndpoints.GET_BY_EMAIL,Users.class,params);
			if(adminUser.getRole().equals("ROLE_ADMIN") && adminUser.getPassword().equals(password)) {
				session.setAttribute("admin", adminUser);
				model.addAttribute("admin",adminUser);
				return "redirect:/admin-dashboard";
			} else {
				return "redirect:/adminlogin";
			}
		} catch (Exception e) {
			logger.info("User(Admin) not logged In");
			return "redirect:/adminlogin";
		}
	}

	
	@RequestMapping("/admin-dashboard")
	public String adminDashboard(HttpSession session,Model model)
	{
		Optional<Object> admin=Optional.ofNullable(session.getAttribute("admin"));
		if(admin.isEmpty()) {
			return "redirect:/adminlogin";
		}
		Users adminUser = (Users)admin.get();
		model.addAttribute("admin",admin);
		return "admin_dashboard";
	}
	
	@RequestMapping("/admin-logout")
	public String adminLogout(HttpSession session)
	{
		session.removeAttribute("admin");
		session.setAttribute("message",new Messages("you have been logged out successfully","alert alert-success"));
		return "adminlogin";
	}
	
	
	@RequestMapping("/admin-profile")
	public String adminProfile(HttpSession session)
	{
		Optional<Object> admin=Optional.ofNullable(session.getAttribute("admin"));
		if(admin.isEmpty()) {
			return "adminlogin";
		} else {
			return "admin_profile";
		}
	}

	@RequestMapping("admin-show-requests")
	public String showRequests(HttpSession session,Model model){
		Optional<Object> admin=Optional.ofNullable(session.getAttribute("admin"));

		if(admin.isEmpty()) {
			return "redirect:/adminlogin";
		}
		try {
			ResponseEntity<Bookings[]> response = restTemplate.getForEntity(BookingsEndpoints.GET_ALL, Bookings[].class);
			List<Bookings> allBookings= Arrays.asList(response.getBody());
			List<Vehicle> allBookedVehicles=new ArrayList<>();
			for(Bookings booking:allBookings){
				Map<String,String> params=new HashMap<>();
				params.put("id",booking.getVehicleId());
				ResponseEntity<Vehicle> rt=restTemplate.getForEntity(VehicleEndpoints.GET_BY_ID,Vehicle.class,params);
				allBookedVehicles.add(rt.getBody());
			}
			model.addAttribute("bookings",allBookings);
			model.addAttribute("vehicles",allBookedVehicles);
			return "admin_show_requests";
		}
		catch (Exception e){
			logger.info("No Bookings as of now");
			model.addAttribute("message",new Messages("No Bookings as of Now","alert alert-danger"));
			return "admin_show_requests";
		}
	}



	@RequestMapping("/admin-approve-request/{bookingId}")
	public String approveRequests(@PathVariable("bookingId") ObjectId id, HttpSession session){
		Optional<Object> admin=Optional.ofNullable(session.getAttribute("admin"));
		if(admin.isEmpty()) {
			return "redirect:/adminlogin";
		}
		Map<String,String> params=new HashMap<>();
		params.put("id", String.valueOf(id));
		ResponseEntity<Bookings> response = restTemplate.getForEntity(BookingsEndpoints.GET_BY_ID, Bookings.class, params);
		Bookings booking= response.getBody();
		booking.setStatus(true);
		restTemplate.postForObject(BookingsEndpoints.POST,booking,Bookings.class);
		logger.info("Booking Aproved Successfully. Booking id: "+booking.get_id());
		return "redirect:/admin-show-requests";
	}



	@RequestMapping("/admin-users-list")
	public String listUsers(HttpSession session,Model model){
		Optional<Object> admin=Optional.ofNullable(session.getAttribute("admin"));
		if(admin.isEmpty()) {
			return "redirect:/adminlogin";
		}
		Users[] response=restTemplate.getForObject(UserEndpoints.GET_ALL,Users[].class);
		List<Users> userList=Arrays.asList(response);
		if(userList.size()==0){
			model.addAttribute("message",new Messages("No Users","alert alert-danger"));
			return "admin_list_users";
		}
		model.addAttribute("users",userList);
		return "admin_list_users";
	}



	@RequestMapping("/admin-search-user")
	public String searchUserByEmail(HttpSession session, Model model,@RequestParam("email") String email){
		Optional<Object> admin=Optional.ofNullable(session.getAttribute("admin"));
		if(admin.isEmpty()) {
			return "redirect:/adminlogin";
		}
		Map<String,String> params =new HashMap<>();
		params.put("email",email);
		Users user=restTemplate.getForObject(UserEndpoints.GET_BY_EMAIL,Users.class,params);
		if(user==null){
			model.addAttribute("message",new Messages("No User with given Email","alert alert-danger"));
			return "admin_list_users";
		}
		List<Users> userList = List.of(user);
		model.addAttribute("users",userList);
		return "admin_list_users";
	}



	@RequestMapping("/admin-stations")
	public String adminStations(HttpSession session, Model model){
		Optional<Object> admin=Optional.ofNullable(session.getAttribute("admin"));
		if(admin.isEmpty()) {
			return "redirect:/adminlogin";
		}
		ResponseEntity<Station[]> response = restTemplate.getForEntity(StationEndpoints.GET_ALL, Station[].class);
		List<Station> stations= Arrays.asList(response.getBody());
		model.addAttribute("stations",stations);
		return "admin_list_stations";
	}



	@RequestMapping("/admin-search-station")
	public String adminSearchStationById(HttpSession session, Model model, @RequestParam("stationId") int stationId){
		Optional<Object> admin=Optional.ofNullable(session.getAttribute("admin"));
		if(admin.isEmpty()) {
			return "redirect:/adminlogin";
		}
		Map<String,Integer> params=new HashMap<>();
		params.put("id",stationId);
		try {
			ResponseEntity<Station> response = restTemplate.getForEntity(StationEndpoints.GET_BY_ID, Station.class, params);
			Station station=response.getBody();
			List<Station> stations=List.of(station);
			model.addAttribute("stations",stations);
			return "admin_list_stations";
		} catch (final HttpClientErrorException e){
			logger.info("Admin Station search failed. No such station. Status code: "+e.getStatusCode());
			model.addAttribute("message",new Messages("No Such station Exists","alert alert-danger"));
			return "admin_list_stations";
		}
	}



	@RequestMapping(value="/admin-add-station",method = RequestMethod.POST)
	public String addStation(HttpSession session, @ModelAttribute Station station){
		Optional<Object> admin=Optional.ofNullable(session.getAttribute("admin"));
		if(admin.isEmpty()) {
			return "redirect:/adminlogin";
		}
		station.setStationId(sequenceGeneratoryService.getSequenceNumber(Station.SEQUENCE_NAME));
		restTemplate.postForObject(StationEndpoints.POST,station,Station.class);
		return "redirect:/admin-stations";
	}


	@RequestMapping("/admin-inventory")
	public String adminInventory(HttpSession session,Model model){
		Optional<Object> admin=Optional.ofNullable(session.getAttribute("admin"));
		if(admin.isEmpty()) {
			return "redirect:/adminlogin";
		}
		ResponseEntity<Inventory[]> response = restTemplate.getForEntity(InventoryEndpoints.GET_ALL, Inventory[].class);
		List<Inventory> inventoryList=Arrays.asList(response.getBody());
		model.addAttribute("inventory",inventoryList);
		ResponseEntity<Station[]> responseEntity = restTemplate.getForEntity(StationEndpoints.GET_ALL, Station[].class);
		List<Station> stations=Arrays.asList(responseEntity.getBody());
		model.addAttribute("stations",stations);
		return "admin_showinventory";
	}

	@RequestMapping(value="/admin-add-inventory", method = RequestMethod.POST)
	public String addInventory(HttpSession session,
							   @RequestParam("stationId") int stationId,
							   @RequestParam("quantity") int quantity,
							   @RequestParam("vehicleType") String vehicleType,
							   @RequestParam("fuelType") String fuelType,
							   @RequestParam("model")  String model,
							   @RequestParam("imageUrl") String imageUrl,
							   @RequestParam("description") String description,
							   @RequestParam("engine") int engine,
							   @RequestParam("mileage") int mileage,
							   @RequestParam("dailyPrice") int dailyPrice,
							   @RequestParam("weeklyPrice") int weeklyPrice){
		Optional<Object> admin=Optional.ofNullable(session.getAttribute("admin"));
		if(admin.isEmpty()) {
			return "redirect:/adminlogin";
		}
		Vehicle vehicle=new Vehicle();
		vehicle.setImageUrl(imageUrl);
		vehicle.setFuelType(fuelType);
		vehicle.setEngine(engine+"cc");
		vehicle.setVehicleType(vehicleType);
		vehicle.set_id(ObjectId.get());
		vehicle.setDescription(description);
		vehicle.setMileage(mileage);
		vehicle.setDailyPrice(dailyPrice);
		vehicle.setWeeklyPrice(weeklyPrice);
		vehicle.setModel(model);

		Inventory inventory=new Inventory();
		inventory.setAvailabilityStatus(true);
		inventory.setQuantity(quantity);
		inventory.setStationId(stationId);
		inventory.setVehicleId(vehicle.get_id());
		inventory.setId(sequenceGeneratoryService.getSequenceNumber(Inventory.SEQUENCE_NAME));
		logger.warn(inventory);
		logger.warn(vehicle);
		restTemplate.postForObject(InventoryEndpoints.POST,inventory,Inventory.class);
		restTemplate.postForObject(VehicleEndpoints.POST,vehicle,Vehicle.class);
		return "redirect:/admin-inventory";
	}

	@RequestMapping("/admin-inventory/vehicle-details/{id}")
	public String inventoryVehicleDetails(HttpSession session,Model model,@PathVariable("id") ObjectId id){
		Optional<Object> admin=Optional.ofNullable(session.getAttribute("admin"));
		if(admin.isEmpty()) {
			return "redirect:/adminlogin";
		}
		Map<String , String> params=new HashMap<>();
		params.put("id", String.valueOf(id));
		ResponseEntity<Vehicle> response = restTemplate.getForEntity(VehicleEndpoints.GET_BY_ID, Vehicle.class, params);
		Vehicle vehicle=response.getBody();
		model.addAttribute("vehicle",vehicle);
		return "admin_inventory_vehicle_details";
	}

}
