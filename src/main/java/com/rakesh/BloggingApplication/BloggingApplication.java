package com.rakesh.BloggingApplication;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class BloggingApplication implements CommandLineRunner {

	public String passwordEncoder() {
		return new BCryptPasswordEncoder().encode("xyz");
	}

	public static void main(String[] args) {
		SpringApplication.run(BloggingApplication.class, args);
	}

	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(passwordEncoder());
	}

}
