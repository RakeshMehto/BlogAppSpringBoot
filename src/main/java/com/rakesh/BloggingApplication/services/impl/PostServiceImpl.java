package com.rakesh.BloggingApplication.services.Impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.rakesh.BloggingApplication.entities.Category;
import com.rakesh.BloggingApplication.entities.Post;
import com.rakesh.BloggingApplication.entities.User;
import com.rakesh.BloggingApplication.exceptions.ResourceNotFoundException;
import com.rakesh.BloggingApplication.payloads.PostDto;
import com.rakesh.BloggingApplication.payloads.PostResponse;
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
	public PostDto getPostById(Integer postId) {
		return this.postToDto(this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "PostId", postId)));
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy) {
		Pageable p = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending());
		PostResponse postResponse = new PostResponse();
		Page<Post> pagepost = this.postRepo.findAll(p);
		List<PostDto> postDtos = pagepost.getContent().stream().map(post -> this.postToDto(post))
				.collect(Collectors.toList());
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagepost.getNumber());
		postResponse.setPageSize(pagepost.getSize());
		postResponse.setTotalElements(pagepost.getTotalPages());
		postResponse.setTotalPages(pagepost.getTotalPages());
		postResponse.setLastPage(pagepost.isLast());
		return postResponse;
	}

	@Override
	public PostResponse getPostByCategory(Integer categoryId, Integer pageNumber, Integer pageSize) {
		Category cat = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
		Pageable p = PageRequest.of(pageNumber, pageSize);
		PostResponse postResponse = new PostResponse();
		Page<Post> pagepost = this.postRepo.findByCategory(cat, p);
		List<PostDto> postDtos = pagepost.getContent().stream().map(post -> this.postToDto(post))
				.collect(Collectors.toList());
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagepost.getNumber());
		postResponse.setPageSize(pagepost.getSize());
		postResponse.setTotalElements(pagepost.getTotalPages());
		postResponse.setTotalPages(pagepost.getTotalPages());
		postResponse.setLastPage(pagepost.isLast());
		return postResponse;
	}

	@Override
	public PostResponse getPostByUser(Integer userId, Integer pageNumber, Integer pageSize) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		Pageable p = PageRequest.of(pageNumber, pageSize);
		PostResponse postResponse = new PostResponse();
		Page<Post> pagepost = this.postRepo.findByUser(user, p);
		List<PostDto> postDtos = pagepost.getContent().stream().map(post -> this.postToDto(post))
				.collect(Collectors.toList());
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagepost.getNumber());
		postResponse.setPageSize(pagepost.getSize());
		postResponse.setTotalElements(pagepost.getTotalPages());
		postResponse.setTotalPages(pagepost.getTotalPages());
		postResponse.setLastPage(pagepost.isLast());
		return postResponse;
	}

	private Post dtoToPost(PostDto postDto) {
		return this.modelMapper.map(postDto, Post.class);
	}

	private PostDto postToDto(Post post) {
		return this.modelMapper.map(post, PostDto.class);
	}

}
