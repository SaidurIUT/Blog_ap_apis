package com.mathanosto.blog.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mathanosto.blog.entities.Comment;
import com.mathanosto.blog.entities.Post;
import com.mathanosto.blog.exceptions.ResourseNotFoundException;
import com.mathanosto.blog.payloads.CommentDto;
import com.mathanosto.blog.repositories.CommentRepo;
import com.mathanosto.blog.repositories.PostRepo;
import com.mathanosto.blog.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private PostRepo postRepo;
	@Autowired
	private CommentRepo commentRepo;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		Post post = postRepo.findById(postId)
				.orElseThrow(() -> new ResourseNotFoundException("Post ", "Post id", postId));

		Comment comment = modelMapper.map(commentDto, Comment.class);

		comment.setPost(post);

		Comment savedComment = commentRepo.save(comment);

		return this.modelMapper.map(savedComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		Comment comment = commentRepo.findById(commentId)
				.orElseThrow(() -> new ResourseNotFoundException("Comment ", "Comment id", commentId));
		commentRepo.delete(comment);

	}

}
