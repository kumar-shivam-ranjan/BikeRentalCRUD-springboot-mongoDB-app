package com.example.BikeRentalSystem.controller;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javax.servlet.http.HttpSession;

import com.example.BikeRentalSystem.RestEndpoints.UserEndpoints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.example.BikeRentalSystem.entities.Users;
import com.example.BikeRentalSystem.messages.Messages;
import com.example.BikeRentalSystem.repository.UserRepository;
import com.example.BikeRentalSystem.service.SequenceGeneratoryService;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Controller
public class HomeController {
	
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private SequenceGeneratoryService sequenceGeneratoryService;
	
	
	@RequestMapping("/")
	public String indexpage() {
		return "redirect:/user/index";
	}

	@RequestMapping("/home")
	public String homepage(Model model,Principal principal)
	{
		try {
			Map<String,String> params =new HashMap<>();
			params.put("email",principal.getName());
			Users user=restTemplate.getForObject(UserEndpoints.GET_BY_EMAIL,Users.class,params);
			model.addAttribute("user",user);
			return "user_home";
		} catch (Exception e) {
			return "home";
		}
	}
	
	@RequestMapping("/signup")
	public String signup(Model model)
	{
		model.addAttribute("user",new Users());
		return "signup";
	}
	
	@RequestMapping("/signin")
	public String login()
	{
		return "login";
	}
	
	@RequestMapping(value="/do_register",method =RequestMethod.POST)
	public String registration(@ModelAttribute Users user,Model model,HttpSession session)
	{

		Map<String,String> params =new HashMap<>();
		params.put("email",user.getEmail());
		Optional<Users> duplicateUser=Optional.ofNullable(restTemplate.getForObject(UserEndpoints.GET_BY_EMAIL,Users.class,params));
		model.addAttribute("user",user);
		if(duplicateUser.isPresent()){
			model.addAttribute("user",user);
			session.setAttribute("message", new Messages("User already Registered !!","alert-danger"));
			return "signup";
		}

		user.setId(sequenceGeneratoryService.getSequenceNumber(Users.SEQUENCE_NAME));
		try {
			user.setRole("ROLE_USER");
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			user.setDrivingLicense(null);
			user.setContact(null);
			restTemplate.postForObject(UserEndpoints.POST,user,Users.class);
			model.addAttribute("user",new Users());
			session.setAttribute("message", new Messages("Successfully Registered","alert-success"));
			return "signup";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("user",user);
			session.setAttribute("message", new Messages("Something went wrong !! "+e.getMessage(),"alert-danger"));
			return "signup";
		}
		
	}
}
