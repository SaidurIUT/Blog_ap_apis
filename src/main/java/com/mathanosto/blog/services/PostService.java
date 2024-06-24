package com.mathanosto.blog.services;

import java.util.List;

import com.mathanosto.blog.entities.Post;
import com.mathanosto.blog.payloads.PostDto;
import com.mathanosto.blog.payloads.PostResponse;

public interface PostService {

	// create

	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

	// Update

	PostDto updatePost(PostDto postDto, Integer postId);

	// Delete

	void deletePost(Integer postId);

	// Get by Id

	PostDto getPostById(Integer postId);

	// Get all posts

	PostResponse getAllPosts(Integer pageNumber, Integer pageSize);

	// Get all posts by category

	List<PostDto> getPostsByCategory(Integer categoryId);

	// Get all posts by user

	List<PostDto> getPostsByUser(Integer userId);

	// Search posts by keyword

	List<Post> searchPosts(String keyword);

}
