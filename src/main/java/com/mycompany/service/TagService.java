package com.mycompany.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	private static Logger logger = LoggerFactory.getLogger(TagService.class);
	
	public Iterable<Tag> getAllTags(){
		return tagDao.findAll();
	}

	public List<String> searchTagsByKeyWord(String keyword) {
		return tagDao.searchTagsByKeyWord(keyword);
	}
	
	public List<Tag> getAllTagsByName(String passedtag) {
		return tagDao.getAllTagsByName(passedtag);
	}
	
	public void addTags(Tag tag) {
		String[] tagStr = tag.getName().replace("#", "").split(" ");
		for (String tag_: tagStr) {
			logger.info("Tag : #" + tag_ + " has saved in database.");
			tagDao.save(new Tag(tag_));
		}
	}
}
