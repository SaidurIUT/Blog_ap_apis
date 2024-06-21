package com.mathanosto.blog.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourseNotFoundException extends RuntimeException {

	String resoursename;
	String fieldName;
	long fieldValue;

	public ResourseNotFoundException(String resoursename, String fieldName, long fieldValue) {
		super(String.format("%s not found with %s : %s", resoursename, fieldName, fieldValue));
		this.resoursename = resoursename;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}

}
