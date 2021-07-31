package com.mycompany.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.dao.ITagFunctionDAO;
import com.mycompany.entity.Tag;

@Service("tagservice")
public class TagService {
	
	@Autowired
	private ITagFunctionDAO tagdao;
	
	public Iterable<Tag> getAllTags(){
		return tagdao.findAll();
	}

}
