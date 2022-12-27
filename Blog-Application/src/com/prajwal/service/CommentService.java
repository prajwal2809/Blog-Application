package com.prajwal.service;

import com.prajwal.entity.Comment;

public interface CommentService {
	public void addComment(Comment comment ,int id,int commentId);
	
	public Comment showComment(int id);

	public void deleteComment(int id);

}
