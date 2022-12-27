package com.prajwal.dao;


import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
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
		List<Tags> listOfTags=theQuery.getResultList();
		String[] tagArray=tagNames.split(",");
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
				existTags.add(tag.getName());
			}

			for(String string:tagArray) {
				if(existTags.contains(string)) {
					alreadyPresent.add(string);
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
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Post> theQuery = null;
        if (searchName != null && searchName.trim().length() > 0) {
        	theQuery = currentSession.createQuery("from Post where  lower(title) like :theName", Post.class);
            theQuery.setParameter("theName", "%" + searchName.toLowerCase() + "%");
        }
        else {
        	theQuery =currentSession.createQuery("from Post", Post.class);  
        }
        List<Post> thePost = theQuery.getResultList();      
        return thePost;
	}
//	@OverridSe
//	public List<Post> getSListOfPosts(int theSortField) {
//		Session currentSession = sessionFactory.getCurrentSession();
//		String theFieldName = null;
//		
//		switch (theSortField) {
//		
////		case SortUtil.TITLE: 
////			theFieldName = "title";
////			break;
////			
////		case SortUtil.AUTHOR:
////			theFieldName = "author";
////			break;
//			
////		case SortUtil.PUBLISHED_AT:
//			theFieldName = "publishedAt";
//		
//		default:
//			theFieldName = "title";

//	@Override
//	@Transactional
//	public List<Post> sortPosts(String sortBy) {
//		SessionFactory factory = this.hibernateTemplate.getSessionFactory();
//		Session session = factory.openSession();
//		Query query = session.createQuery("from Post order by creationDate " + sortBy);
//		List<Post> posts = query.getResultList();
//		return posts;
//	}
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
	public List<Post> filterPost(String author) {

		Session currentSession = sessionFactory.getCurrentSession();
//		SessionFactory factory = this.hibernateTemplate.getSessionFactory();
//		SessionFactory
//		Session session = factory.openSession();
		Query query=currentSession.createQuery("from Tags where name like :s");
		query.setParameter("s", author);
		List<Tags> tags=query.getResultList();
		//System.out.println(tags);
		List<Post> posts=new ArrayList<Post>();
		if(!tags.isEmpty()) {
		for(Tags tag:tags) {
			if(tag.getName().equals(author)) {
				List<Post> list=tag.getPosts();
				for(Post post:list) {
					posts.add(post);
				}	
			}
		}
		}
		return posts;
	
	}
}
	
	
	





