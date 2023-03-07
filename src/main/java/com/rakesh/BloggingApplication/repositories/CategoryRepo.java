package com.rakesh.BloggingApplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rakesh.BloggingApplication.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
