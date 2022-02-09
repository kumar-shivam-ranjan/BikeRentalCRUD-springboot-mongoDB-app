package com.example.BikeRentalSystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.example.BikeRentalSystem.entities.Users;
import com.example.BikeRentalSystem.repository.UserRepository;

public class UserDetailsServiceImpl  implements UserDetailsService{

	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Users user = userRepository.getUserByEmail(email);
		if(user==null) {
			throw new UsernameNotFoundException("Could not found user with given email");
		}
		CustomUserDetails customUserDetails = new CustomUserDetails(user);
		return customUserDetails; 
	}

}
