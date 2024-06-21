package com.mathanosto.blog.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mathanosto.blog.payloads.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(ResourseNotFoundException.class)
	public ResponseEntity<ApiResponse> ressourceNotFoundExceptionHandelar(ResourseNotFoundException ex) {
		String message = ex.getMessage();
		ApiResponse apiResponse = new ApiResponse(message, false);
		return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
	}

}
