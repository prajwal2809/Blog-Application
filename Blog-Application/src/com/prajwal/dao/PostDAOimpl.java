package com.prajwal.dao;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.prajwal.entity.Post;
import com.prajwal.entity.Tags;


@Repository
public class PostDAOimpl implements PostDAO{

	@Autowired
	private SessionFactory sessionFactory;
	@Override
	@Transactional
	public List<Post> getPosts() {
		
		Session currentSession=sessionFactory.getCurrentSession();
		
		Query<Post> theQuery=currentSession.createQuery("from Post",Post.class);
		
		List<Post> Posts=theQuery.getResultList();
		 
		return Posts;
	
	}
	@Override
	@Transactional
	public void savePost(Post thePost) {
		Session currentSession=sessionFactory.getCurrentSession();
	
		currentSession.save(thePost);
	}
	@Override
	@Transactional
	public Post showPost(int theId) {
		Session currentSession=sessionFactory.getCurrentSession();
		Post post=currentSession.get(Post.class, theId);
		return post;
		
	}
	@Override
	@Transactional
	public void updatePost(int theID) {

	Session currentSession=sessionFactory.getCurrentSession();
	currentSession.saveOrUpdate(theID);

	}
	@Override
	@Transactional
	public void deletePost(int theID) {
		Session currentSession=sessionFactory.getCurrentSession();
		currentSession.delete(currentSession.get(Post.class, theID));
		
	}
	@Override
	@Transactional
	public void saveTags(String tagNames,int postId) {
		Session currentSession=sessionFactory.getCurrentSession();
		Post post=currentSession.get(Post.class,postId);
//		Session currentSession=sessionFactory.getCurrentSession();
//		
		System.out.println(tagNames+"  "+postId);
		
		
		Query<Tags> theQuery=currentSession.createQuery("from Tags ",Tags.class);
//		
		List<Tags> listOfTags=theQuery.getResultList(); //A B C D
		String[] tagArray=tagNames.split(",");	//"B" "C" "F"
		List<String> alreadyPresent=new ArrayList<>();
//		for(String string:tagArray) {
//			Post.addtags()
//		}
		if(listOfTags.isEmpty()) {
			for(String string:tagArray) {
				Tags tag=new Tags();
				tag.setName(string);
				tag.addPost(post);
				currentSession.save(tag);
			}
		}

		else{
			List<String> existTags=new ArrayList<>();
			for(Tags tag:listOfTags) {
				existTags.add(tag.getName());   //"A" "B" "C" D"
			}

			for(String string:tagArray) {
				if(existTags.contains(string)) {
					alreadyPresent.add(string);   //"B "C"
				}
				else {
					Tags tag=new Tags();
					tag.setName(string);
					tag.addPost(post);
					currentSession.save(tag);
				}
			}
		
		}
		for(Tags tag:listOfTags) {
			if(alreadyPresent.contains(tag.getName())){
				tag.addPost(post);
			}
		}
	}
	@Override
	@Transactional
	public List<Post> searchPosts(String searchName) {
		System.out.println(searchName);
		List<Integer> postId=new ArrayList<>();
		Session currentSession = sessionFactory.getCurrentSession();
		Query theQuery = currentSession.createSQLQuery("SELECT id from post where lower(title) like lower('%"+searchName+"%') OR lower(content) like lower('%"+searchName+"%')  union SELECT post_id from post_tags where tag_id in (SELECT id From tags where lower(name) like lower('%"+searchName+"%') )");
//            query.setParameter("theSearchName", "%" + theSearchName.toLowerCase() + "%");, Post.class);
//        theQuery.setParameter("searchName","%" + searchName.toLowerCase() + "%");
//            theQuery.setParameter("theName", "%" + searchName.toLowerCase() + "%");
//        System.out.println(theQuery);
        
        postId=theQuery.getResultList();
        System.out.println(postId);
        List<Post> thePost=new ArrayList<>();
        for(int id:postId)
        {
        	thePost.add(showPost(id));
        }
        return thePost;
	}

	@Override
	public List<Post> getListOfPosts(String theSortField) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		String query=""; 
		
		if(theSortField.equals("asc")) {
			query += "from Post order by published_at ASC";
		}
		else {
			query += "from Post order by published_at DESC";
		}
		Query<Post> theQuery = currentSession.createQuery(query, Post.class);
		List<Post> thePost = theQuery.getResultList();		
		return thePost;
	}

	@Override
	public List<String> getAuthorList() {
		
		Session currentSession=sessionFactory.getCurrentSession();
		List<String> authorList=new ArrayList<>();
		Query theQuery = currentSession.createSQLQuery("SELECT author from post ");
//		List<Integer> postId=theQuery.getResultList();
//		for(int id:postId) {
//			authorList.add(showPost(id));
//		}
		authorList=theQuery.getResultList();
		
		return authorList;
		
	}
	@Override
	public List<String> getTagList() {
		
		
		Session currentSession=sessionFactory.getCurrentSession();
		List<String> tagList=new ArrayList<>();
		Query theQuery = currentSession.createSQLQuery("SELECT name from tags");
		tagList=theQuery.getResultList();
		return tagList;
		
	}
	@Override
	public List<Post> filterPost(String authorName, String tagName) {
		
		Session currentSession=sessionFactory.getCurrentSession();
		List<Post> postList=new ArrayList<>();
		Query theQuery = currentSession.createSQLQuery("SELECT id from post where author='"+authorName+"' intersect SELECT post_id from post_tags where tag_id in ( SELECT id From tags where name='"+tagName+"') ");
		List<Integer> postId=theQuery.getResultList();
		String[] tagArray=authorName.split(",");
		
//		HashSet<Integer> postId= theQuery.getResultList();
		System.out.println(postId);
		for(int id:postId) {
			postList.add(showPost(id));
		}
		return postList;
		
	}
	
}

//@Override
//public List<Post> filterPost(String author) {
//	
//	System.out.println(author);
//	Session currentSession = sessionFactory.getCurrentSession();
////	SessionFactory factory = this.hibernateTemplate.getSessionFactory();
////	SessionFactory
////	Session session = factory.openSession();
//	System.out.println(author);
//	Query query=currentSession.createQuery("from Tags where name like :s");
//	query.setParameter("s", author);
//	List<Tags> tags=query.getResultList();
//	//System.out.println(tags);
//	List<Post> posts=new ArrayList<Post>();
//	if(!tags.isEmpty()) {
//	for(Tags tag:tags) {
//		if(tag.getName().equals(author)) {
//			List<Post> list=tag.getPosts();
//			for(Post post:list) {
//				posts.add(post);
//			}	
//		}
//	}
//	}
//	return posts;
//
//}

//@OverridSe
//public List<Post> getSListOfPosts(int theSortField) {
//	Session currentSession = sessionFactory.getCurrentSession();
//	String theFieldName = null;
//	
//	switch (theSortField) {
//	
////	case SortUtil.TITLE: 
////		theFieldName = "title";
////		break;
////		
////	case SortUtil.AUTHOR:
////		theFieldName = "author";
////		break;
//		
////	case SortUtil.PUBLISHED_AT:
//		theFieldName = "publishedAt";
//	
//	default:
//		theFieldName = "title";

//@Override
//@Transactional
//public List<Post> sortPosts(String sortBy) {
//	SessionFactory factory = this.hibernateTemplate.getSessionFactory();
//	Session session = factory.openSession();
//	Query query = session.createQuery("from Post order by creationDate " + sortBy);
//	List<Post> posts = query.getResultList();
//	return posts;
//}
	
	
	





