package com.rakesh.BloggingApplication.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.rakesh.BloggingApplication.entities.Category;
import com.rakesh.BloggingApplication.entities.Post;
import com.rakesh.BloggingApplication.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer> {

	Page<Post> findByUser(User user, Pageable p);

	Page<Post> findByCategory(Category category, Pageable p);

}
