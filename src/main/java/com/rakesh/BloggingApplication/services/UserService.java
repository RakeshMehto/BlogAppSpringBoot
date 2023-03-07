package com.rakesh.BloggingApplication.services;

import java.util.List;

//import com.rakesh.BloggingApplication.entities.User;
import com.rakesh.BloggingApplication.payloads.UserDto;

public interface UserService {
	UserDto createUser(UserDto user);

	UserDto updateUser(UserDto user, Integer userId);

	UserDto getUserById(Integer userId);

	List<UserDto> getAllUsers();

	void deleteUser(Integer userId);
}
