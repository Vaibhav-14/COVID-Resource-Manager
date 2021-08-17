package com.mycompany.controller;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.mycompany.config.MyUserDetails;
import com.mycompany.dao.IRoleFunctionDAO;
import com.mycompany.entity.Comment;
import com.mycompany.entity.Post;
import com.mycompany.entity.Role;
import com.mycompany.entity.Tag;
import com.mycompany.entity.User;
import com.mycompany.service.CommentService;
import com.mycompany.service.PostService;
import com.mycompany.service.UserService;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
public class CommentControllerTest {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PostService postService; 
	
	@Autowired
	private CommentService commentService ; 
	
	@Autowired
	private IRoleFunctionDAO roleDao ; 
	
	@Autowired
	private CommentController commentController ; 
	@Autowired
	private MockMvc mockMvc;
	
	
	
	@Test
	@Order(1)
	public void contextLoads() throws Exception{
		assertThat(commentController).isNotNull();
		Role role = new Role() ; 
		role.setId(1);
		role.setRole("USER");
		roleDao.save(role) ; 
		role.setId(2);
		role.setRole("ADMIN");
		roleDao.save(role) ; 
	}
	
	@Test
	@Transactional
	@Order(2)
	public void saveCommentTest() throws Exception {
		// Creating Post
		Post post = new Post() ; 
		post.setType("Required") ; 

		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		    Date parsedDate = dateFormat.parse("2000-01-01 00:00:01");
		    Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
			post.setDateTime(timestamp);
		}catch(Exception e) {
			System.out.println("Error : In Allocation the Time Stamp for Post");
			e.printStackTrace();
		}
		post.setMessage("medicine required");
		
		// Creating User for Post
		User user = new User() ; 
		user.setUsername("Champ");
		user.setEmail("Champ@gmail.com");
		user.setFirstname("Champ");
		user.setLastname("OK");
		user.setPassword("Thor");
		user.setMobile("1123456789") ; 
		try {
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		    Date parsedDate = dateFormat.parse(String.valueOf("2000-01-01"));
		    user.setDateOfBirth(parsedDate);
		} catch(Exception e) { 
			System.out.println("Error : In Allocation of DOB to user");
			e.printStackTrace();
		}
		user.setGender("male");
		user.setEnabled(1);
		user.setWarnings(0);
		// Saving User in Database 
		userService.addUser(user);
		
		// User Authentication
		UsernamePasswordAuthenticationToken authReq
			    = new UsernamePasswordAuthenticationToken("Champ", "Thor");
		AuthenticationManager auth = new AuthenticationManager() {
					
			@Override
			public Authentication authenticate(Authentication authentication) throws AuthenticationException {
				return authentication;
			}
		};
			    
		SecurityContext sc = SecurityContextHolder.getContext();
		sc.setAuthentication(auth.authenticate(authReq));
		
		// Adding User --> Post
		//post.setUser(user);
		
		// Generating Tags
		Set<Tag> tagsObj = new HashSet<Tag>() ; 
		tagsObj.add(new Tag("#Available" , null )) ; 
		tagsObj.add(new Tag("#Urgent" , null )) ; 
		
		// Adding Tags --> Post
		post.setTags(tagsObj);
		post.setTagStr(post.getDateTime().toString()+", Urgent, ");
		// Saving Post to Database 
		postService.addPost(post);
		List<Post> posts = postService.findPostByUsername("Champ") ; 
		Post p = posts.get(0) ; 

		Comment comment = new Comment() ; 
		comment.setUser(user);
		comment.setPost(p);
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		    Date parsedDate = dateFormat.parse("2000-01-01 00:00:01");
		    Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
			comment.setDateTime(timestamp);
		}catch(Exception e) {
			System.out.println("Error : In Allocation the Time Stamp for Post");
			e.printStackTrace();
		}
		comment.setContent("This is comment");
		
		// Adding Comment 
		System.out.println(comment);
		
		//assertDoesNotThrow( () -> commentController.saveComment(comment)) ;
		
	}
	
	
	
	@Test
	@Order(4)
	public void deleteRoles() {
		Role role = new Role() ; 
		role.setId(1);
		role.setRole("USER");
		roleDao.delete(role);
		role.setId(2);
		role.setRole("ADMIN");
		roleDao.delete(role);
	}
	

}