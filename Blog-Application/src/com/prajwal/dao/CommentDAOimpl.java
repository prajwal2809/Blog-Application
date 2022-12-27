package com.prajwal.dao;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.SessionFactory;

import com.prajwal.entity.Comment;

@Repository
public class CommentDAOimpl implements CommentDAO {
	@Autowired
	private SessionFactory sessionFactory;

	@Override

	public void addComment(Comment comment) {
		Session currentSession=sessionFactory.getCurrentSession();
		currentSession.save(comment);
	}

	@Override

	public Comment showComment(int id) {
		
		Session currentSession=sessionFactory.getCurrentSession();
		Comment comment=currentSession.get(Comment.class, id);
		return  comment;
	}

	@Override

	public void deleteComment(int id) {
		Session currentSession=sessionFactory.getCurrentSession();
		System.out.println(id);
		currentSession.delete(currentSession.get(Comment.class, id));
	}

	@Override

	public void updateComment(Comment comment) {
		Session currentSession=sessionFactory.getCurrentSession();
		currentSession.update(comment);
	}


}
