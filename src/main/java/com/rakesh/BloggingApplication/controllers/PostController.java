package com.rakesh.BloggingApplication.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rakesh.BloggingApplication.payloads.PostDto;
import com.rakesh.BloggingApplication.services.PostService;

@RestController
@RequestMapping("/api/")
public class PostController {

	@Autowired
	private PostService postService;

	@PostMapping("/user/{userId}/category/{categoryId}/posts/")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId,
			@PathVariable Integer categoryId) {

		return new ResponseEntity<PostDto>(this.postService.createPost(postDto, categoryId, userId),
				HttpStatus.CREATED);
	}
//
//	@PutMapping("/{id}")
//	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer id)
//	{
//		
//	}

}
