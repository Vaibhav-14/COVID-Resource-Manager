package com.mycompany.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.mycompany.dao.IRoleFunctionDAO;
import com.mycompany.dao.IUserFunctionDAO;
import com.mycompany.entity.Comment;
import com.mycompany.entity.Post;
import com.mycompany.entity.Role;
import com.mycompany.entity.Tag;
import com.mycompany.entity.User;
import com.mycompany.exception.IncorrectUserException;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class CommentServiceTest {
	
	@Autowired
	private CommentService commentService ; 
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private IUserFunctionDAO userDao;
	
	@Autowired
	private PostService postService; 
	
	@Autowired
	private IRoleFunctionDAO roleDao ; 
	
	@Test
	@Order(1)
	public void contextLoads() {
		assertThat(commentService).isNotNull() ; 
		
	}
	
	@Test
	@Transactional
	@Order(2)
	public void addCommentTest() {
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
		assertDoesNotThrow(() -> commentService.addComment(comment));
		
	}
	
	@Test
	@Order(3)
	public void findAllCommentsByUserID() {
		User user = userService.getUserFromUsername("Champ") ; 
		List<Comment> comments = commentService.findAllCommentsByUserID(user.getId()) ; 
		assertTrue(comments.size() > 0 ) ;
	}
	
	@Test
	@Order(4)
	public void deleteCommentNotValidUser() {
		User user = new User() ; 
		user.setUsername("Thor");
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
//		Set<Role> roles = new HashSet<>() ; 
//		roles.add(roleDao.findByName("ADMIN")) ;
//		user.setRoles(roles);
		// Saving User in Database 
		userService.addUser(user);
		
		// User Authentication
		UsernamePasswordAuthenticationToken authReq
			      = new UsernamePasswordAuthenticationToken("Thor", "Thor");
		AuthenticationManager auth = new AuthenticationManager() {
					
		@Override
		public Authentication authenticate(Authentication authentication) throws AuthenticationException {
				return authentication;
			}
		};    
		SecurityContext sc = SecurityContextHolder.getContext();
		sc.setAuthentication(auth.authenticate(authReq));
		
		// Find All Comments
		user = userService.getUserFromUsername("Champ") ; 
		List<Comment> comments = commentService.findAllCommentsByUserID(user.getId()) ; 
		for (Comment comment : comments) {
			assertThrows(IncorrectUserException.class , () -> commentService.deleteComment(comment.getId())) ; 
		}
		
		userService.deleteUserAccount("Thor");
	}
	
	
	@Test
	@Order(5)
	public void deleteCommentTest() {
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
		
		User user = userService.getUserFromUsername("Champ") ; 
		commentService.deleteComment(user.getId());
		List<Comment> comments = commentService.findAllCommentsByUserID(user.getId()) ; 
		assertTrue(comments.size() == 0 ) ;
		
		userService.deleteUserAccount("Champ");
		User user1 = userService.getUserFromUsername("Champ") ; 
		if (user1 != null) {
			userDao.delete(user1);
		}
	}
	
}

