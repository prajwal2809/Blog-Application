package com.prajwal.service;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public interface TagService {
	 
	
	public void createTags(String tagNames,int tagId);

}
