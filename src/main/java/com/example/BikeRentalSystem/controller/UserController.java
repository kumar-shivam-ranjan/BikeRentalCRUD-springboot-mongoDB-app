package com.example.BikeRentalSystem.controller;

import java.security.Principal;
import java.util.*;
import java.util.concurrent.TimeUnit;
import com.example.BikeRentalSystem.RestEndpoints.*;
import com.example.BikeRentalSystem.entities.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import com.example.BikeRentalSystem.messages.Messages;
import com.example.BikeRentalSystem.repository.UserRepository;
import springfox.documentation.annotations.ApiIgnore;



@ApiIgnore
@Controller
@RequestMapping("/user")
public class UserController {


	@Autowired
	private RestTemplate restTemplate;

	private static final Logger logger= LogManager.getLogger(UserController.class);



	@RequestMapping(value="/index")
	public String userDashboard(Model model, Principal principal) {
		try {
			Map<String,String> params =new HashMap<>();
			params.put("email",principal.getName());
			Users user=restTemplate.getForObject(UserEndpoints.GET_BY_EMAIL,Users.class,params);
			model.addAttribute("user",user);
			return "user_home";
		} catch (Exception e) {
			return "login";
		}	
	}
	
	
	
	@RequestMapping("/list-station")
	public String processCityQuery(@RequestParam("city") String city,Model model,Principal principal) {
		try {
			Map<String,String> params =new HashMap<>();
			params.put("email",principal.getName());
			Users user=restTemplate.getForObject(UserEndpoints.GET_BY_EMAIL,Users.class,params);
			model.addAttribute("user",user);
		}catch (Exception e) {
			return "login";
		}
		try {
			HashMap<String,String> params=new HashMap<>();
			params.put("city",city);
			ResponseEntity<Station[]> response=restTemplate.getForEntity(StationEndpoints.GET_BY_CITY,Station[].class,params);
			Station[] stations=response.getBody();
			List<Station> cityStations=Arrays.asList(stations);
			model.addAttribute("stations",cityStations);
			return "user_home";
		} catch (final HttpClientErrorException e) {
			logger.error("No such City found. Status Code:"+e.getStatusCode());
			model.addAttribute("message404",new Messages("No Station in this City","alert alert-danger"));
			return "user_home";
		}
	}
	
	
	@RequestMapping("/list-station/{id}")
	public String processListBike(@PathVariable("id") int id,Model model,Principal principal) {
		try {
			Map<String,String> params =new HashMap<>();
			params.put("email",principal.getName());
			Users user=restTemplate.getForObject(UserEndpoints.GET_BY_EMAIL,Users.class,params);
			model.addAttribute("user",user);
		}
		catch (Exception e) {
			return "login";
		}
		try {
			HashMap<String,String> params=new HashMap<>();
			params.put("id", String.valueOf(id));
			ResponseEntity<Inventory[]> response=restTemplate.getForEntity(InventoryEndpoints.GET_BY_STATIONID,Inventory[].class,params);
			Inventory[] inventories=response.getBody();
			List<Inventory> inventoryList=Arrays.asList(inventories);
			List<Vehicle> vehicleList=new ArrayList<Vehicle>();
			for(Inventory i:inventoryList) {
				HashMap<String,String > parameter=new HashMap<>();
				parameter.put("id",i.getVehicleId());
				ResponseEntity<Vehicle> rt=restTemplate.getForEntity(VehicleEndpoints.GET_BY_ID,Vehicle.class,parameter);
				vehicleList.add(rt.getBody());
			}
			model.addAttribute("vehicles",vehicleList);
			model.addAttribute("sid",id);
			return "user_bikedashboard";
		} catch (final HttpClientErrorException e) {
			logger.error("No Bikes in this Station. Status Code: "+e.getStatusCode());
			model.addAttribute("message404", new Messages("No Bikes in this Station", "alert alert-danger"));
			return "user_home";
		}
	}



	@RequestMapping("book-bike/{bikeId}/{stationId}")
	public String bookBike(@PathVariable("bikeId") String bikeId, @PathVariable("stationId") int stationId,Model model , Principal principal){
		try {
			Map<String,String> params =new HashMap<>();
			params.put("email",principal.getName());
			Users user=restTemplate.getForObject(UserEndpoints.GET_BY_EMAIL,Users.class,params);
			model.addAttribute("user",user);
		}
		catch (Exception e) {
			return "login";	
		}

		HashMap<String,String> params=new HashMap<>();
		params.put("id",bikeId);
		ResponseEntity<Vehicle> rt=restTemplate.getForEntity(VehicleEndpoints.GET_BY_ID,Vehicle.class,params);
		Vehicle bike=rt.getBody();
		model.addAttribute("bike",bike);
		model.addAttribute("stationId",stationId);
		return "user_bookbike";
	}



	@RequestMapping("/profile")
	public String userProfile(Principal principal,Model model) {
		try {
			Map<String,String> params =new HashMap<>();
			params.put("email",principal.getName());
			Users user=restTemplate.getForObject(UserEndpoints.GET_BY_EMAIL,Users.class,params);
			model.addAttribute("user",user);
		}
		catch (Exception e) {
			return "login";
		}
		return "user_profile";
	}


	@RequestMapping(value="/save-contact",method = RequestMethod.POST)
	public String updateProfile(@ModelAttribute Users user, Principal principal, Model model){
		Users currentUser;
		try {
			Map<String,String> params =new HashMap<>();
			params.put("email",principal.getName());
			currentUser = restTemplate.getForObject(UserEndpoints.GET_BY_EMAIL,Users.class,params);
		}
		catch (Exception e) {
			return "login";
		}
		try {
			currentUser.setContact(user.getContact());
			currentUser.setDrivingLicense(user.getDrivingLicense());
			Map<String , Integer >  params=new HashMap<>();
			params.put("id",currentUser.getId());
			restTemplate.put(UserEndpoints.PUT_ENDPOINT,currentUser,params);
			model.addAttribute("user",currentUser);
			model.addAttribute("message",new Messages("Information Saved Successfully","alert alert-success"));
			return "user_profile";
		}
		catch (Exception e){
			e.printStackTrace();
			model.addAttribute("message",new Messages("Couldn't save Information","alert alert-danger"));
			model.addAttribute("user",currentUser);
			return "user_profile";
		}
	}

	@RequestMapping(value="/process-booking",method=RequestMethod.POST)
	public String processBooking(@ModelAttribute Bookings booking , Principal principal, Model model){
		try {
			Map<String,String> params =new HashMap<>();
			params.put("email",principal.getName());
			Users user=restTemplate.getForObject(UserEndpoints.GET_BY_EMAIL,Users.class,params);
			model.addAttribute("user",user);
		}
		catch (Exception e) {
			return "login";
		}
		Date checkIn=booking.getCheckInDate();
		Date checkOut=booking.getCheckOutDate();
		HashMap<String,String> params=new HashMap<>();
		params.put("id",booking.getVehicleId());
		ResponseEntity<Vehicle> rt=restTemplate.getForEntity(VehicleEndpoints.GET_BY_ID,Vehicle.class,params);
		Vehicle bike=rt.getBody();
		model.addAttribute("bike",bike);
		model.addAttribute("stationId",booking.getStationId());

		if(checkIn.after(checkOut)){
			model.addAttribute("message",new Messages("CheckIn Date must be before check out Date","alert alert-danger"));
		}
		else{
			booking.setStatus(false);
			booking.setId(String.valueOf(ObjectId.get()));
			long diff=checkIn.getTime()-checkOut.getTime();
			Long NoOfDays=TimeUnit.DAYS.convert(diff,TimeUnit.MILLISECONDS);
			if(NoOfDays<0) NoOfDays=NoOfDays*-1;
			long price=(NoOfDays/7)*bike.getWeeklyPrice()+(NoOfDays%7)*bike.getDailyPrice();
			booking.setCost(price);
			this.restTemplate.postForObject(BookingsEndpoints.POST,booking,Bookings.class);
			logger.info("Booking done successfully. Booking Id: "+booking.getId());
			model.addAttribute("message",new Messages("Booked Successfully !! Please Wait for admin to approve your booking","alert alert-success"));
		}
		return "user_bookbike";

	}


	@RequestMapping("/show-bookings")
	public String showBookings(Principal principal,Model model){
		try {
			Map<String,String> params =new HashMap<>();
			params.put("email",principal.getName());
			Users user=restTemplate.getForObject(UserEndpoints.GET_BY_EMAIL,Users.class,params);
			model.addAttribute("user",user);
		}
		catch (Exception e) {
			return "login";
		}
		HashMap<String,String> params=new HashMap<>();
		params.put("email",principal.getName());

		try {
			ResponseEntity<Bookings[]> response=  this.restTemplate.getForEntity(BookingsEndpoints.GET_BY_EMAIL,Bookings[].class,params);
			List<Bookings> userBookings=Arrays.asList(response.getBody());
			List<Vehicle> userBookedVehicles=new ArrayList<>();
			for(Bookings booking:userBookings){
				HashMap<String, String> parameter=new HashMap<>();
				parameter.put("id",booking.getVehicleId());
				ResponseEntity<Vehicle> rt=restTemplate.getForEntity(VehicleEndpoints.GET_BY_ID,Vehicle.class,parameter);
				userBookedVehicles.add(rt.getBody());
			}
			model.addAttribute("bookings",userBookings);
			model.addAttribute("vehicles",userBookedVehicles);
			return "user_showbookings";
		}
		catch (Exception e){
			model.addAttribute("message",new Messages("No Bookings as of Now","alert alert-danger"));
			return "user_showbookings";
		}
	}

	@RequestMapping("/delete-booking/{bookingId}")
	public String deleteBooking(@PathVariable("bookingId") ObjectId id, Principal principal, Model model){
		try {
			Map<String,String> params =new HashMap<>();
			params.put("email",principal.getName());
			Users user=restTemplate.getForObject(UserEndpoints.GET_BY_EMAIL,Users.class,params);
			model.addAttribute("user",user);
		}
		catch (Exception e) {
			return "login";
		}
		HashMap<String,String> params=new HashMap<>();
		params.put("id", String.valueOf(id));
		this.restTemplate.delete(BookingsEndpoints.DELETE_BY_ID,params);
		logger.info("Booking Deleted Successfully");
		return "redirect:/user/show-bookings";
	}

}
