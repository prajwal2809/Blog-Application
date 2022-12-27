package com.prajwal.dao;


import com.prajwal.entity.Comment;

public interface CommentDAO {
	public void addComment(Comment comment );
	public Comment showComment(int id);
	public void deleteComment(int id);
	public void updateComment(Comment comment);

}
