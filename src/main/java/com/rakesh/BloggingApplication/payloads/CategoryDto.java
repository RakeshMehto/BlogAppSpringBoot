package com.rakesh.BloggingApplication.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
	private int categoryId;
	@NotEmpty
	@Size(min = 4, message = "Must be minimum 4 characters")
	private String categoryTitle;
	@NotEmpty
	@Size(max = 50, message = "Not more than 50 characters")
	private String categoryDescription;
}
