package com.rakesh.BloggingApplication.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rakesh.BloggingApplication.entities.Category;
import com.rakesh.BloggingApplication.entities.Post;
import com.rakesh.BloggingApplication.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer> {

	List<Post> findByUser(User user);

	List<Post> findByCategory(Category category);

}
