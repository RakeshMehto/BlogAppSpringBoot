package com.rakesh.BloggingApplication.security;

//import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rakesh.BloggingApplication.entities.User;
import com.rakesh.BloggingApplication.exceptions.ResourceNotFoundException;
import com.rakesh.BloggingApplication.repositories.UserRepo;

@Service
public class CustomDetailService implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// loading user from database by user name
		User user = this.userRepo.findByEmail(username)
				.orElseThrow(() -> new ResourceNotFoundException("User", "email", username));

		return user;
	}

}
