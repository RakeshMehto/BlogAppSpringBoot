package com.rakesh.BloggingApplication.controllers;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.rakesh.BloggingApplication.payloads.ApiResponse;
import com.rakesh.BloggingApplication.payloads.CategoryDto;
import com.rakesh.BloggingApplication.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategory() {
		return new ResponseEntity<>(this.categoryService.getAllCategory(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CategoryDto> getUserById(@PathVariable Integer id) {
		return new ResponseEntity<CategoryDto>(this.categoryService.getCategory(id), HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
		return new ResponseEntity<CategoryDto>(this.categoryService.createCategory(categoryDto), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<CategoryDto> updateCategory(@PathVariable Integer id,
			@Valid @RequestBody CategoryDto categoryDto) {
		return new ResponseEntity<CategoryDto>(this.categoryService.updateCategory(categoryDto, id), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer id) {
		this.categoryService.deleteCategory(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category deleted successfully!", true), HttpStatus.OK);
	}
}
