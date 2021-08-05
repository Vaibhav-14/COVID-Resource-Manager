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
import com.mycompany.entity.User;

@Transactional
@Service("postService")
public class PostService {
	
	@Autowired
	private IPostFunctionDAO postDao;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TagService tagService;

	public IPostFunctionDAO getDao() {
		return postDao;
	}

	public void setDao(IPostFunctionDAO dao) {
		this.postDao = dao;
	}
	
	public void addPost(Post post) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		post.setUser(userService.getUserFromUsername(auth.getName()));
		post.setDateTime(new Timestamp(System.currentTimeMillis()));
		addTagsToPost(post);
		postDao.save(post);
	}
	
	// retrieve tags from tagStr
	private void addTagsToPost(Post post) {
		Set<Tag> tags = new HashSet<Tag>();
		Iterable<Tag> db_tags = tagService.getAllTags();
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
	
	public void deletePost(int id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.getUserFromUsername(auth.getName());

		if(postDao.findById(id).get().getUser().getId() == user.getId())
			postDao.deleteById(id);
	}
	
	public List<Post> getAllPost(){
		return postDao.findAllByOrderByDateTimeDesc();
	}
	
	public List<Post> findPostByUsername(String username) {
		return postDao.findPostByUser(userService.getUserFromUsername(username));
	}
	
	public Set<Post> getPostOnSearch(String searchEntry) {
		Set<Post> searchList = new HashSet<>();
		
		//finding posts by username
		searchList.addAll(findPostByUsername(searchEntry));
		
		List<String> associatedTags = tagService.searchTagsByKeyWord(searchEntry);
		
		for(String currentTag: associatedTags)
		{
			List<Integer> postsWithcurrentTag = tagService.getListOfAllPostswithTag(currentTag);
			for(Integer integer: postsWithcurrentTag)
				searchList.add(getPostById(integer));		
		}
	    
	    return searchList;
	}
	
	
}
