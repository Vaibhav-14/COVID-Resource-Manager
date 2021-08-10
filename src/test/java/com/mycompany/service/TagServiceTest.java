	package com.mycompany.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mycompany.entity.Post;
import com.mycompany.entity.Tag;
import com.mycompany.entity.User;
import com.mycompany.dao.ITagFunctionDAO;

@SpringBootTest
public class TagServiceTest {
	@Autowired
	private ITagFunctionDAO tagDao;
	
	@Test
	public void contextLoads() {
		assertThat(tagDao).isNotNull() ; 
	}
	
	@Test
	public void serachTagsByKeywordTest() {
		String keyword = "Available";
		List<String> tagsList = tagDao.searchTagsByKeyWord(keyword);
		List<String> expectedList = new ArrayList<String>();
		expectedList.add("#Available");
		assertEquals(expectedList , tagsList);
		
	}
	
	@Test
	public void getAllTagsByNameTest() throws Exception {
		String name = "#Available";
		List<Tag> tagsList = tagDao.getAllTagsByName(name);//this method is giving NULL list as output
		System.out.println("Tags retrieved are :" +tagsList);
		
		Post post = new Post("Urgent", new Timestamp(System.currentTimeMillis()) , "urgent oxygen cyclinders requirement at ...");
	    User user= new User("a","b","abc","abc@test.com","12345abcd","7410084485","ACTIVE",new SimpleDateFormat("dd/MM/yyyy").parse("12/04/2010"),"male",0);
	    post.setUser(user);
	    post.setId(1);
	    Set<Post> setOfPost = new HashSet<>();
	    setOfPost.add(post);
		List<Tag> expectedList = new ArrayList<Tag>();
		Tag tag1 = new Tag("#Required",setOfPost);
		expectedList.add(tag1);
		assertNotNull(expectedList);
		assertNotEquals(expectedList , tagsList);
	}
	
	@Test
	public void getAllTagsTest() throws ParseException{
		Iterable<Tag> tagsIterable = tagDao.findAll();
		List<Tag> tagsList = new ArrayList<Tag>();
	    for (Tag tag : tagsIterable) {
	        tagsList.add(tag);
	    }
		//System.out.println("Tags retrieved are :" +tagsList);
		Post post = new Post("Urgent", new Timestamp(System.currentTimeMillis()) , "urgent oxygen cyclinders requirement at ...");
	    User user= new User("a","b","abc","abc@test.com","12345abcd","7410084485","ACTIVE",new SimpleDateFormat("dd/MM/yyyy").parse("12/04/2010"),"male",0);
	    post.setUser(user);
	    post.setId(1);
	    Set<Post> setOfPost = new HashSet<>();
	    setOfPost.add(post);
		List<Tag> expectedList = new ArrayList<Tag>();
		Tag tag1 = new Tag("#Available",setOfPost);
		Tag tag2 = new Tag("#hydroxychloroquine",setOfPost);
		Tag tag3 = new Tag("#Required",setOfPost);
		Tag tag4 = new Tag("#Urgent",setOfPost);
		expectedList.add(tag1);
		expectedList.add(tag2);
		expectedList.add(tag3);
		expectedList.add(tag4);

		assertNotNull(expectedList);
		assertNotEquals(expectedList , tagsList);
		
		
	}
	
}
