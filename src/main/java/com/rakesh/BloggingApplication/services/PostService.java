package com.rakesh.BloggingApplication.services;

import java.util.List;

import com.rakesh.BloggingApplication.payloads.PostDto;

public interface PostService {

	PostDto createPost(PostDto postDto, Integer categoryId, Integer userId);

	PostDto updatePost(PostDto postDto, Integer postId);

	void deletePost(Integer postId);

	List<PostDto> getAllPost();

	PostDto getPostById(Integer postId);

	List<PostDto> getPostByCategory(Integer categoryId);

	List<PostDto> getPostByUser(Integer userId);

}
