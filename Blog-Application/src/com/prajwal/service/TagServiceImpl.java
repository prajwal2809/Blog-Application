package com.prajwal.service;

import org.apache.jasper.compiler.tagplugin.TagPlugin;
import org.apache.jasper.compiler.tagplugin.TagPluginContext;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class TagServiceImpl implements TagService {
//	@Autowired
//	private SessionFactory sessionFactory;
	
	
	
	@Override
	public void createTags(String tagNames, int tagId) {
		
		
//		Session currentSession=sessionFactory.getCurrentSession();
//		TagDAO.createTags(tagNames,tagId);
	}

}
