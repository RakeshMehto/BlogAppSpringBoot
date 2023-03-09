package com.rakesh.BloggingApplication.services.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rakesh.BloggingApplication.entities.User;
import com.rakesh.BloggingApplication.exceptions.ResourceNotFoundException;
import com.rakesh.BloggingApplication.payloads.UserDto;
import com.rakesh.BloggingApplication.repositories.UserRepo;
import com.rakesh.BloggingApplication.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepo userRepos;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto createUser(UserDto userDto) {
		User user = this.dtoToUser(userDto);
		User savedUser = this.userRepos.save(user);
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user = this.userRepos.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		user.setAbout(userDto.getAbout());
		user.setEmail(userDto.getEmail());
		user.setName(userDto.getName());
		user.setPassword(userDto.getPassword());
		UserDto updatedUserDto = userToDto(this.userRepos.save(user));
		return updatedUserDto;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user = this.userRepos.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		return userToDto(this.userRepos.save(user));
	}

	@Override
	public List<UserDto> getAllUsers() {
		return this.userRepos.findAll().stream().map(user -> userToDto(user)).collect(Collectors.toList());
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = this.userRepos.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		this.userRepos.delete(user);

	}

	private User dtoToUser(UserDto userDto) {

		User user = this.modelMapper.map(userDto, User.class);
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());
		return user;

	}

	private UserDto userToDto(User user) {
		UserDto userDto = new UserDto();
//		userDto.setId(user.getId());
//		userDto.setAbout(user.getAbout());
//		userDto.setEmail(user.getEmail());
//		userDto.setPassword(user.getPassword());
//		userDto.setName(user.getName());
		userDto = this.modelMapper.map(user, UserDto.class);
		return userDto;
	}
}
