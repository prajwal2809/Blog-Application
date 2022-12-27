package com.prajwal.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prajwal.dao.CommentDAO;
import com.prajwal.entity.Comment;
import com.prajwal.entity.Post;
@Service
public class CommentServiceImpl implements CommentService {
//	Date currentDate=new Date(System.currentTimeMillis());
	
	@Autowired
	private CommentDAO commentDAO;
	@Autowired
	private PostService postService;
	@Override
	@Transactional
	public void addComment(Comment comment, int id,int commentId) {
		
		java.sql.Date date=new java.sql.Date(System.currentTimeMillis());
		
		if(commentId!=0) {
			Comment lastComment=showComment(commentId);
			lastComment.setName(comment.getName());
			lastComment.setEmail(comment.getEmail());
			lastComment.setComment(comment.getComment());
			lastComment.setUpdated_at(date);
			commentDAO.updateComment(lastComment);
		
		}else {
			Post post=postService.showPost(id);
			comment.setCreated_at(date);
			comment.setUpdated_at(date);
			comment.setPost(post);
			post.getComments().add(comment);
			commentDAO.addComment(comment);
	
		}
	}
	@Override
	@Transactional
	public Comment showComment(int id) {
		
		return commentDAO.showComment(id);
	}
	@Override
	@Transactional
	public void deleteComment(int id) {
		commentDAO.deleteComment(id);
		
	}
	
	
}
