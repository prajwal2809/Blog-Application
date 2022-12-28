package com.prajwal.dao;
import java.util.List;
//
//import org.springframework.stereotype.Repository;
//
//import com.prajwal.entity.Comment;
import com.prajwal.entity.Post;



public interface PostDAO {
	
	public List<Post> getPosts();
	
	public void savePost(Post thePost);
	
	public Post showPost(int theID);
	public void updatePost(int theID);
	public void deletePost(int theID);

	public void saveTags(String tagNames,int id);

	public List<Post> searchPosts(String searchName);
	public List<Post> getListOfPosts(String sortField);
//	public List<Post> filterPost(String author);

	public List<String> getAuthorList();
	
	public List<String> getTagList();

	public List<Post> filterPost(String author, String tagName);

//	public List<Post> sortPosts(String sortBy);

}
