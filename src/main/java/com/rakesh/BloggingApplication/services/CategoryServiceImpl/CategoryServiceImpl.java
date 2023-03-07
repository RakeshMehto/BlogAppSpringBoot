package com.rakesh.BloggingApplication.services.CategoryServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rakesh.BloggingApplication.entities.Category;
import com.rakesh.BloggingApplication.exceptions.ResourceNotFoundException;
import com.rakesh.BloggingApplication.payloads.CategoryDto;
import com.rakesh.BloggingApplication.repositories.CategoryRepo;
import com.rakesh.BloggingApplication.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		return this.categoryToDto(this.categoryRepo.save(this.dtoToCategory(categoryDto)));
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		return this.categoryToDto(this.categoryRepo.save(category));
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
		this.categoryRepo.delete(category);
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		return this.categoryToDto(this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId)));
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		return this.categoryRepo.findAll().stream().map(category -> this.categoryToDto(category))
				.collect(Collectors.toList());
	}

	public Category dtoToCategory(CategoryDto categoryDto) {
		return this.modelMapper.map(categoryDto, Category.class);
	}

	public CategoryDto categoryToDto(Category category) {
		return this.modelMapper.map(category, CategoryDto.class);
	}
}
