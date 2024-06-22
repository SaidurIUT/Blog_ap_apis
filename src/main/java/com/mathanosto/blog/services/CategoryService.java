package com.mathanosto.blog.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mathanosto.blog.payloads.CategoryDto;

@Service
public interface CategoryService {
	// create
	public CategoryDto createCategory(CategoryDto categoryDto);

	// update
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);

	// delete
	public void deleteCategory(Integer categoryId);

	// get by id
	public CategoryDto getCategoryById(Integer categoryId);

	// get all
	public List<CategoryDto> getAllCategories();

}
