package com.mycompany.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.mycompany.dao.IPostFunctionDAO;
import com.mycompany.entity.Post;
import com.mycompany.entity.Tag;
import com.mycompany.entity.User;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class PostServiceTest{
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PostService postService; 
	
	@Autowired
	private IPostFunctionDAO postDao;
	
	@Test
	@Order(1)
	public void contextLoads() {
		assertThat(userService).isNotNull() ; 
		assertThat(postService).isNotNull() ; 
		assertThat(postDao).isNotNull() ; 
	}
	
//	@Test
//	@Order(2)
//	public void addPost() {
//		// Creating Post
//		Post post = new Post() ; 
//		post.setId(1);
//		post.setType("Urgent") ; 
//		try {
//			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//		    Date parsedDate = dateFormat.parse("2000-01-01 00:00:01");
//		    Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
//			post.setDateTime(timestamp);
//		}catch(Exception e) {
//			System.out.println("Error : In Allocation the Time Stamp for Post");
//			e.printStackTrace();
//		}
//		post.setMessage("medicine required");
//		
//		// Creating User for Post
//		User user = new User() ; 
//		user.setId(1);
//		user.setUsername("Champ");
//		user.setEmail("Champ@gmail.com");
//		user.setFirstname("Champ");
//		user.setLastname("OK");
//		user.setPassword("Thor");
//		user.setMobile("1123456789") ; 
//		user.setAccountStatus("active");
//		try {
//		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//		    Date parsedDate = dateFormat.parse(String.valueOf("2000-01-01"));
//		    user.setDateOfBirth(parsedDate);
//		} catch(Exception e) { 
//			System.out.println("Error : In Allocation of DOB to user");
//			e.printStackTrace();
//		}
//		user.setGender("male");
//		user.setEnabled(1);
//		
//		// Saving User in Database 
//		userService.addUser(user);
//		
//		// Adding User --> Post
//		post.setUser(user);
//		
//		// Generating Tags
//		Set<Tag> tagsObj = new HashSet<Tag>() ; 
//		tagsObj.add(new Tag("#Available" , null )) ; 
//		tagsObj.add(new Tag("#Urgent" , null )) ; 
//		
//		// Adding Tags --> Post
//		post.setTags(tagsObj);
//		
//		// Saving Post to Database 
//		postDao.save(post) ; 
//		
//		System.out.println("Done Till Here");
//		
//		// Check ! Is Valid Post
//		assertEquals(postService.getPostById(1).getMessage() , post.getMessage());
//	}
//	
//	@Test
//	@Order(3)
//	public void updatePost() {
//		Post post = postService.getPostById(1) ; 
//		String message = "All Fine" ; 
//		post.setMessage(message);
//		postService.updatePost(post);
//		assertTrue(postService.getPostById(1).getMessage().equals(message));
//	}
	
	@Test 
	@Order(4)
	public void getAllPost() {
		assertTrue(postService.getAllPost().size() > 0 ) ; 
	}
	
	@Test
	@Order(5)
	public void findPostByUsername() {
		String username = "Champ" ; 
		List<Post> posts = postService.findPostByUsername(username) ; 
		for(Post post : posts) {
			assertTrue(post.getUser().getUsername().equals(username)) ; 
		}
	} 
	
	@Test
	@Order(6)
	public void searchPost(){
		// Positive Test Cases 
		// By username
		assertTrue(postService.findPostByUsername("champ").size() > 0 , "Result should be greater than zero" ) ; 
		
		// Negative Test Cases 
		// By username
		assertTrue(postService.findPostByUsername("cham").size() == 0  , "Result should be zero" ) ; 

	}
	
//	@Test
//	@Order(7)
//	public void testPostUpdateByType() throws Exception {
//       String type = "Required";
//       Post post = new Post("Required", new Timestamp(System.currentTimeMillis()) , "urgent oxygen cyclinders requirement at ...");
//       User user= new User("a","b","abc","abc@test.com","12345abcd","7410084485","ACTIVE",new SimpleDateFormat("dd/MM/yyyy").parse("12/04/2010"),"male",0);
//       post.setUser(user);
//       post.setId(1);
//       Post savedPost = postDao.save(post);
//       assertNotNull(post);
//       Post updatePost = (Post) postDao.findPostByUser(user);
//       assertThat(savedPost.getType()).isEqualTo(type);
//       assertThat(updatePost.getType()).isEqualTo(type);
//  
//   }
	
}
