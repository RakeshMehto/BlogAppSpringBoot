package com.rakesh.BloggingApplication.services.Impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rakesh.BloggingApplication.entities.Category;
import com.rakesh.BloggingApplication.entities.Post;
import com.rakesh.BloggingApplication.entities.User;
import com.rakesh.BloggingApplication.exceptions.ResourceNotFoundException;
import com.rakesh.BloggingApplication.payloads.PostDto;
import com.rakesh.BloggingApplication.repositories.CategoryRepo;
import com.rakesh.BloggingApplication.repositories.PostRepo;
import com.rakesh.BloggingApplication.repositories.UserRepo;
import com.rakesh.BloggingApplication.services.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PostRepo postRepo;
	@Autowired
	private UserRepo userRepo;

	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public PostDto createPost(PostDto postDto, Integer categoryId, Integer userId) {

		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("category", "category id", categoryId));
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("USer", "User Id", userId));
		Post post = this.dtoToPost(postDto);
		post.setAddedDate(new Date());
		post.setImageName("default.png");
		post.setUser(user);
		post.setCategory(category);
		return this.postToDto(this.postRepo.save(post));
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "PostId", postId));
		post.setAddedDate(postDto.getDate());
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());

		return this.postToDto(this.postRepo.save(post));
	}

	@Override
	public void deletePost(Integer postId) {

		this.postRepo.delete(this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "PostId", postId)));

	}

	@Override
	public List<PostDto> getAllPost() {
		return this.postRepo.findAll().stream().map(post -> this.postToDto(post)).collect(Collectors.toList());
	}

	@Override
	public PostDto getPostById(Integer postId) {
		return this.postToDto(this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "PostId", postId)));
	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {
		Category cat = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Cat", "cat id", categoryId));
		return this.postRepo.findByCategory(cat).stream().map(post -> this.postToDto(post))
				.collect(Collectors.toList());
	}

	@Override
	public List<PostDto> getPostByUser(Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		return this.postRepo.findByUser(user).stream().map(post -> this.postToDto(post)).collect(Collectors.toList());
	}

	private Post dtoToPost(PostDto postDto) {
		return this.modelMapper.map(postDto, Post.class);
	}

	private PostDto postToDto(Post post) {
		return this.modelMapper.map(post, PostDto.class);
	}

}
