package com.prajwal.service;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prajwal.dao.PostDAO;
import com.prajwal.entity.Post;
import com.prajwal.entity.Tags;

@Service
public class PostServiceImpl implements PostService {
	
	
	Date currentDate=new Date(System.currentTimeMillis());
	
	@Autowired
	private PostDAO postDAO;
	
	
	Post post;
	@Override
	@Transactional
	public List<Post> getPosts(){
		return postDAO.getPosts();
	}
	@Override
	@Transactional
	public void savePost(Post thePost,int id,String submitType) {
		
		if(id!=0) {
			Post post=showPost(id);
			post.setTitle(thePost.getTitle());
			post.setContent(thePost.getContent());
			post.setUpdated_at(thePost.getUpdated_at());
			post.setIs_published(true);
			postDAO.savePost(post);
		}
		else {
			if(submitType=="publish") {
				thePost.setPublished_at(currentDate);
				thePost.setIs_published(true);
			}
			
			if(thePost.getContent().length()>=25) {
				thePost.setExcerpt(thePost.getContent().substring(0,25));
			}
			else {
				thePost.setExcerpt(thePost.getContent());
			}
			postDAO.savePost(thePost);
			
		}
		
	}
	@Override
	@Transactional
	public Post showPost(int theID) {
		
		return postDAO.showPost(theID);
	}
	@Override
	@Transactional
	public void updatePost(int theId) {
//		post.setUpdated_at(date);
		postDAO.updatePost(theId);
	}
	@Override
	@Transactional
	public void deletePost(int theID) {
		postDAO.deletePost(theID);
	}
//	@Override
//	public int addPost(Post thePost, int id) {
//		int postId=thePost.getId(id);
//		return postId;
//	}
	@Override
	@Transactional
	public void saveTags(String tagNames,int id) {
		postDAO.saveTags(tagNames,id);
		
		
	}
	@Override
	@Transactional
	public List<Post> searchPosts(String searchName) {
		
		return postDAO.searchPosts(searchName);
	}
	@Override
	@Transactional
	public List<Post> getListOfPosts(String sortField) {
		return postDAO.getListOfPosts(sortField);
	}
//	@Override
//	public List<Post> sortPosts(String sortBy) {
//		
//		return postDAO.sortPosts(sortBy);
//		}



}
