package com.mathanosto.blog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mathanosto.blog.entities.Category;
import com.mathanosto.blog.entities.Post;
import com.mathanosto.blog.entities.User;
import com.mathanosto.blog.exceptions.ResourseNotFoundException;
import com.mathanosto.blog.payloads.PostDto;
import com.mathanosto.blog.repositories.CategoryRepo;
import com.mathanosto.blog.repositories.PostRepo;
import com.mathanosto.blog.repositories.UserRepo;
import com.mathanosto.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService {
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourseNotFoundException("User ", "User id", userId));

		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourseNotFoundException("Category", "category id ", categoryId));

		Post post = this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);

		Post newPost = this.postRepo.save(post);

		return this.modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {

		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourseNotFoundException("Post", "Post id ", postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());

		Post updatedPost = this.postRepo.save(post);
		return this.modelMapper.map(updatedPost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {

		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourseNotFoundException("Post", "Post id ", postId));
		this.postRepo.delete(post);

	}

	@Override
	public PostDto getPostById(Integer postId) {

		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourseNotFoundException("Post", "Post id ", postId));

		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getAllPosts() {

		List<Post> allPosts = this.postRepo.findAll();
		List<PostDto> postDtos = allPosts.stream().map(post -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) {

		Category cat = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourseNotFoundException("Category", "category id ", categoryId));
		List<Post> posts = this.postRepo.findByCategory(cat);

		List<PostDto> postDtos = posts.stream().map(post -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		return postDtos;
	}

	@Override
	public List<PostDto> getPostsByUser(Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourseNotFoundException("User ", "User id", userId));
		List<Post> posts = this.postRepo.findByUser(user);
		List<PostDto> postDtos = posts.stream().map(post -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<Post> searchPosts(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

}