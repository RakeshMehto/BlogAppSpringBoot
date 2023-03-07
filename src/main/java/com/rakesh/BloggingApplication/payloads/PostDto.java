package com.rakesh.BloggingApplication.payloads;

import java.sql.Date;

import com.rakesh.BloggingApplication.entities.Category;
import com.rakesh.BloggingApplication.entities.User;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class PostDto {

	private int postId;
	@NotEmpty
	@Size(max = 50, min = 10, message = "Tilte must be 10 to 50 characters")
	private String title;
	private String content;
	private Date addedDate;
	private Category category;
	private User user;

}
