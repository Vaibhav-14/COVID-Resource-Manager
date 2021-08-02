package com.mycompany.service;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.dao.IPostFunctionDAO;
import com.mycompany.entity.Post;
import com.mycompany.entity.Tag;

@Transactional
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
	
	public void addPost(Post post) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		post.setUser(userservice.getUserFromUsername(auth.getName()));
		post.setDateTime(new Timestamp(System.currentTimeMillis()));
		addTagsToPost(post);
		postDao.save(post);
	}
	
	// retrieve tags from tagStr
	private void addTagsToPost(Post post) {
		Set<Tag> tags = new HashSet<Tag>();
		Iterable<Tag> db_tags = tagservice.getAllTags();
		for (String s : post.getTagStr().split(", ")) {
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
	}

	public Post getPostById(int id) {
		Post post = postDao.findById(id).get();
		StringBuffer str = new StringBuffer();
		for (Tag tag : post.getTags()) {
			str.append(tag.getName() + ", ");
		}
		post.setTagStr(str.toString()); 
		return post;
	}

	public void updatePost(Post post) {
		addTagsToPost(post);
		postDao.save(post);
	}
	
	public List<Post> getAllPost(){
		return postDao.findAllByOrderByDateTimeDesc();
	}
	
}
