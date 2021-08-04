
package com.mycompany;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.controller.HomeController;
import com.mycompany.controller.PostController;
import com.mycompany.controller.UserController;
import com.mycompany.dao.IPostFunctionDAO;
import com.mycompany.dao.IUserFunctionDAO;
import com.mycompany.entity.Post;
import com.mycompany.entity.User;
import com.mycompany.service.PostService;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;



@SpringBootTest
@AutoConfigureMockMvc
public class CovidResourceManagerApplicationTests {
	
	@Autowired
	private PostService service;
	
	@MockBean
	private IPostFunctionDAO postDao;
	
	@Autowired
	private PostController postController;
	@Autowired
	private HomeController homeController;
	@Autowired
	private UserController userController;
	
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private PostService postservice;
	
	@MockBean
	private IUserFunctionDAO userDao;
	
	@Test
	public void contextLoads() throws Exception {
		assertThat(postController).isNotNull();
		assertThat(homeController).isNotNull();
		assertThat(userController).isNotNull();
	}
	
	@Test
	public void checkHTTPResponse() throws Exception {
		this.mockMvc.perform(get("/user/login")).andExpect(status().isOk());
		this.mockMvc.perform(get("/user/register")).andExpect(status().isOk());
	}
	
	@Test
	public void testPostUpdateByMessage() {
		Post post = postservice.getPostById(1) ; 
		String message = "All Fine" ; 
		post.setMessage(message);
		postservice.updatePost(post);
		assertTrue(postservice.getPostById(1).getMessage().equals(message));
	}
	
	@Test
	 public void testPostUpdateByType() throws Exception {
        String type = "Required";
        Post post = new Post("Required", new Timestamp(System.currentTimeMillis()) , "urgent oxygen cyclinders requirement at ...");
        User user= new User("a","b","abc","abc@test.com","12345abcd","7410084485","ACTIVE",new SimpleDateFormat("dd/MM/yyyy").parse("12/04/2010"),"male",0);
        post.setUser(user);
        post.setId(1);
        Post savedPost = postDao.save(post);
        assertNotNull(post);
        Post updatePost = (Post) postDao.findPostByUser(user);
        assertThat(savedPost.getType()).isEqualTo(type);
        assertThat(updatePost.getType()).isEqualTo(type);
   
    }
	
}