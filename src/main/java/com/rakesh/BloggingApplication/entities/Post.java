package com.rakesh.BloggingApplication.entities;

import java.util.Date;

//import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int postId;
	@Column(name = "post_title", length = 100, nullable = false)
	private String title;
	@Column(length = 1000)
	private String content;
	private String imageName;
	private Date addedDate;
	@JoinColumn(name = "category_id", nullable = false)
	@ManyToOne
	private Category category;
	@JoinColumn(name = "user_id", nullable = false)
	@ManyToOne
	private User user;

}
