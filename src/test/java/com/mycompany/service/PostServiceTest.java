package com.mycompany.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import com.mycompany.dao.IPostFunctionDAO;
import com.mycompany.dao.IUserFunctionDAO;
import com.mycompany.entity.Post;
import com.mycompany.entity.Tag;
import com.mycompany.entity.User;
import com.mycompany.exception.IncorrectUserException;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class PostServiceTest{
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private IUserFunctionDAO userDao;
	
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
	
	@Test
	@Order(2)
	public void addPost() {
		
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
		// Check ! Is Valid Post
		try {
			List<Post> posts = postService.findPostByUsername("Champ") ; 
			Post p = posts.get(0) ; 
			assertEquals(postService.getPostById(p.getId()).getMessage() , post.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Test
	@Transactional
	@Order(3)
	public void updatePost() {
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
		List<Post> posts = postService.findPostByUsername("Champ") ; 
		Post post = posts.get(0) ; 
		System.out.println(post);
		String message = "All Fine" ; 
		post.setMessage(message);
		post.setTagStr("Urgent, ");
		postService.updatePost(post);
		
	}

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
		assertTrue(postService.findPostByUsername("noUser").size() == 0  , "Result should be zero" ) ; 

	}
	
//	@Order(7)
//	@Test
//	public void testPostUpdate() throws Exception {
//       String type = "Required";
//       Post post = new Post("Urgent", new Timestamp(System.currentTimeMillis()) , "urgent oxygen cyclinders requirement at ...");
//       User user= new User("a","b","abc","abc@test.com","12345abcd","7410084485","ACTIVE",new SimpleDateFormat("dd/MM/yyyy").parse("12/04/2010"),"male",0);
//       post.setUser(user);
//       post.setId(1);
//       assertNotNull(post);
//       //Updating Type.....
//       post.setType(type);
//       Post savedPost = postDao.save(post);
//       assertThat(savedPost.getType()).isEqualTo(type);
//       
//       //updating Message.....
//       String message = "All Fine" ; 
//       post.setMessage(message);
//       savedPost = postDao.save(post);
//       assertThat(savedPost.getMessage()).isEqualTo(message);
//
//   }
	
	@Test
	@Order(7)
	public void testSetDao() {
		PostService postService = new PostService();
		postService.setDao(postDao);
		assertTrue(postService.getDao() == this.postDao);
	}
	
	@Test
	@Order(8)
	public void testGetDao() {
		PostService postService = new PostService();
		postService.setDao(postDao);
		assertTrue(postService.getDao() == this.postDao);
	}
	
	@Test
	@Order(9)
	public void getPostOnSearchTest() {
		Set<Post> posts = postService.getPostOnSearch("Urgent") ; 
		assertTrue(posts.size() >= 0 ) ; 
	}
	
	
	
	@Test
	@Order(11)
	public void testGetterSetter() {
		assertDoesNotThrow(() -> postService.setDao(postService.getDao()));
	}
	
	@Test
	@Order(12)
	public void reportPostTest() {
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
				
		List<Post> posts = postService.findPostByUsername("Champ") ; 
		Post post = posts.get(0) ; 
		assertDoesNotThrow(() -> postService.reportPost(post.getId()));
	}
	
	@Test
	@Order(13)
	public void deletePostTest() {
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
		
		List<Post> posts = postService.findPostByUsername("Champ") ; 
		for(Post post : posts) {
			postService.deletePost(post.getId());
		}
		
		userService.deleteUserAccount("Champ");
		User user = userService.getUser("Champ") ; 
		if (user != null) {
			userDao.delete(user);
		}
	}
	
	
	
}