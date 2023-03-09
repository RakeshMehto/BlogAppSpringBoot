package com.rakesh.BloggingApplication.payloads;

import java.util.Date;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {

	@NotEmpty
	@Size(max = 50, min = 10, message = "Tilte must be 10 to 50 characters")
	private String title;
	private String content;
	private String imageName;
	private Date date;
	private UserDto user;
	private CategoryDto category;

}
