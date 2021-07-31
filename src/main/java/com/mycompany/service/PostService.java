package com.mycompany.service;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.dao.IPostFunctionDAO;
import com.mycompany.entity.Post;
import com.mycompany.entity.Tag;

@Service("postService")
public class PostService {
	
	@Autowired
	private IPostFunctionDAO postDao;
	
	@Autowired
	private UserService userservice;
	
	@Autowired
	private TagService tagservice;

	public IPostFunctionDAO getDao() {
		return postDao;
	}

	public void setDao(IPostFunctionDAO dao) {
		this.postDao = dao;
	}
	
	public void addPost(Post post, String username) {
		post.setUser(userservice.getUserFromUsername(username));
		post.setDateTime(new Timestamp(System.currentTimeMillis()));
		Set<Tag> tags = new HashSet<Tag>();
		Iterable<Tag> db_tags = tagservice.getAllTags();
		for (String s : post.getTagStr().split(" ")) {
			int flag = 0;
			for (Tag tag : db_tags) {
				if(tag.getName().equals(s)) {
					flag = 1;
					tags.add(tag);
					break;
				}
			}
			if(flag == 0) {
				Tag tag = new Tag();
				tag.setName(s);
				Set<Post> tag_post = new HashSet<Post>();
				tag_post.add(post);
				tags.add(tag);
			}
		}
		post.setTags(tags);
		postDao.save(post);
	}
	
}
