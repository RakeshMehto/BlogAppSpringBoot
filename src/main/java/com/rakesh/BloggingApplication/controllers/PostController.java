package com.rakesh.BloggingApplication.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rakesh.BloggingApplication.payloads.ApiResponse;
import com.rakesh.BloggingApplication.payloads.PostDto;
import com.rakesh.BloggingApplication.payloads.PostResponse;
import com.rakesh.BloggingApplication.services.PostService;

@RestController
@RequestMapping("/api/")
public class PostController {

	@Autowired
	private PostService postService;

	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId,
			@PathVariable Integer categoryId) {
		return new ResponseEntity<PostDto>(this.postService.createPost(postDto, categoryId, userId),
				HttpStatus.CREATED);
	}

	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<PostResponse> getPostByUser(@PathVariable Integer userId,
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
		return new ResponseEntity<>(this.postService.getPostByUser(userId, pageNumber, pageSize), HttpStatus.OK);
	}

	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<PostResponse> getPostByCategory(@PathVariable Integer categoryId,
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
		return new ResponseEntity<>(this.postService.getPostByCategory(categoryId, pageNumber, pageSize),
				HttpStatus.OK);
	}

	@GetMapping("/posts/{id}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer id) {
		return new ResponseEntity<PostDto>(this.postService.getPostById(id), HttpStatus.OK);
	}

	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPost(
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = "postId", required = false) String sortBy) {
		return new ResponseEntity<>(this.postService.getAllPost(pageNumber, pageSize, sortBy), HttpStatus.OK);
	}

	@PutMapping("/posts/{id}")
	public ResponseEntity<PostDto> updatePost(@PathVariable Integer id, @RequestBody PostDto postDto) {
		return new ResponseEntity<PostDto>(this.postService.updatePost(postDto, id), HttpStatus.OK);
	}

	@DeleteMapping("/posts/{id}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer id) {
		this.postService.deletePost(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("post deleted successfully!", true), HttpStatus.OK);
	}
}
