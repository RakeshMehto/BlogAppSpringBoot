package com.rakesh.BloggingApplication.services;

import java.util.List;

import com.rakesh.BloggingApplication.payloads.PostDto;
import com.rakesh.BloggingApplication.payloads.UserDto;

public interface PostService {
//	PostDto createUser(PostDto user);
//
//	UserDto updateUser(PostDto user, Integer userId);
//
//	UserDto getUserById(Integer userId);
//
//	List<UserDto> getAllUsers();
//
//	void deleteUser(Integer userId);
	PostDto createPost(PostDto postDto);

	PostDto updatePost()
}
