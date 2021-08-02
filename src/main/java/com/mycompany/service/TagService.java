package com.mycompany.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.dao.ITagFunctionDAO;
import com.mycompany.entity.Tag;

@Transactional
@Service("tagService")
public class TagService {
	
	@Autowired
	private ITagFunctionDAO tagDao;
	
	public Iterable<Tag> getAllTags(){
		return tagDao.findAll();
	}

	public List<String> searchTagsByKeyWord(String keyword) {
		return tagDao.searchTagsByKeyWord(keyword);
	}
	

}
