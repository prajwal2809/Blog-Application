package com.prajwal.service;

import java.util.List;

import com.prajwal.entity.Post;
import com.prajwal.entity.Tags;



public interface PostService {
	
	public List<Post> getPosts();
	
	public void savePost(Post thePost,int id,String submitType);
	
	public Post showPost(int theId);
	
	public void updatePost(int theId);
	
	public void deletePost(int theId);

	public void saveTags(String tagNames,int id);

	public List<Post> searchPosts(String searchName);
	
	public List<Post> getListOfPosts(String sortField);
	
	public List<Post> filterPost(String author,String tagName);

	public List<String> getTagList();

	public List<String> getAuthorList();

//	public List<Post> sortPosts(String sortBy);
	
//	public int addPost(Post thePost,int id);
}
