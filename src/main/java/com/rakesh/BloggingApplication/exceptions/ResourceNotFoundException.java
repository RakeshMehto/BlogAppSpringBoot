package com.rakesh.BloggingApplication.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {
	/**
	 * 
	 */
//	private static final long serialVersionUID = 3741573014970882528L;
	String resourceName;
	String fieldname;
	long fieldvalue;

	public ResourceNotFoundException(String resourceName, String fieldname, long fieldvalue) {
		super(String.format("%s not found with %s : %s", resourceName, fieldname, fieldvalue));
		this.resourceName = resourceName;
		this.fieldname = fieldname;
		this.fieldvalue = fieldvalue;
	}

}
