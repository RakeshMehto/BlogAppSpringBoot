package com.rakesh.BloggingApplication.services;

import com.rakesh.BloggingApplication.payloads.PostDto;
import com.rakesh.BloggingApplication.payloads.PostResponse;

public interface PostService {

	PostDto createPost(PostDto postDto, Integer categoryId, Integer userId);

	PostDto updatePost(PostDto postDto, Integer postId);

	void deletePost(Integer postId);

	PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy);

	PostDto getPostById(Integer postId);

	PostResponse getPostByCategory(Integer categoryId, Integer pageNumber, Integer pageSize);

	PostResponse getPostByUser(Integer userId, Integer pageNumber, Integer pageSize);

}
