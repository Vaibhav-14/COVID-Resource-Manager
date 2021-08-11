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
import com.mycompany.dao.IRoleFunctionDAO;
import com.mycompany.dao.IUserFunctionDAO;
import com.mycompany.entity.Comment;
import com.mycompany.entity.Post;
import com.mycompany.entity.Role;
import com.mycompany.entity.Tag;
import com.mycompany.entity.User;
import com.mycompany.exception.IncorrectUserException;

@Transactional
@Service("postService")
public class PostService {
	
	@Autowired
	private IPostFunctionDAO postDao;
	
	@Autowired
	private IRoleFunctionDAO roleDao;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TagService tagService;
		
	@Autowired
	private NotificationService notificationService;

	public IPostFunctionDAO getDao() {
		return postDao;
	}

	public void setDao(IPostFunctionDAO dao) {
		this.postDao = dao;
	}
	
	public void addPost(Post post) {
		Set<User> mentionedUsers = userService.getUsersFromString(post.getMessage());
		
		User loggedInUser = userService.getLoggedInUser();
		
		mentionedUsers.remove(loggedInUser);
		post.setUser(loggedInUser);
		post.setDateTime(new Timestamp(System.currentTimeMillis()));
		addTagsToPost(post);
		postDao.save(post);
		
		String activityType = "@" + loggedInUser.getUsername() + " mentioned you in a post";

		notificationService.saveNotification(loggedInUser, activityType, "post", "post/" + post.getId(), mentionedUsers);
	}

	// retrieve tags from tagStr
	private void addTagsToPost(Post post) {
		Set<Tag> tags = new HashSet<Tag>();
		Iterable<Tag> db_tags = tagService.getAllTags();
		for (String tagString : post.getTagStr().replaceAll("#", "").split(" ")) {
			int flag = 0;
			for (Tag tag : db_tags) {
				if(tag.getName().equals(tagString)) {
					flag = 1;
					tags.add(tag);
					break;
				}
			}
			if(flag == 0) {
				Tag tag = new Tag();
				tag.setName(tagString);
				Set<Post> tag_post = new HashSet<Post>();
				tag_post.add(post);
				tags.add(tag);
			}
		}
		post.setTags(tags);
	}

	public Post getPostById(int id) {
		Post post = postDao.findById(id).get();
		User user = userService.getLoggedInUser();


//		since any user should be allowed to share and view any post, the commented code below is not optimal and needs to be discussed upon
		
//		Role role = roleDao.findByName("ADMIN");
//		if(post.getUser().getId() != user.getId() && !user.getRoles().contains(role))
//			throw new IncorrectUserException("This post doesn't belong to User " + user.getUsername());
		
		StringBuffer str = new StringBuffer();
		for (Tag tag : post.getTags()) {
			str.append("#" + tag.getName() + " ");
		}
		post.setTagStr(str.toString()); 
		return post;
	}
	

	public void updatePost(Post post) throws IncorrectUserException {
		Post oldPost = getPostById(post.getId());
		post.setDateTime(oldPost.getDateTime());
		addTagsToPost(post);
		User loggedInUser = userService.getLoggedInUser();

		if(post.getUser().getId() == loggedInUser.getId())
			postDao.save(post);
		else
			throw new IncorrectUserException("We are sorry but you do not have that permission.");

	}
	
	public void deletePost(int id) throws IncorrectUserException {
		User user = userService.getLoggedInUser();
		Role role = roleDao.findByName("ADMIN");
		boolean isAdmin = false;
		Post post = postDao.findPostById(id);
		User postUser = post.getUser();
		if(postUser.getId() == user.getId() || (isAdmin = user.getRoles().contains(role))) {
			postDao.deleteById(id);
			if (isAdmin) {
				String activityType = "Your post violets the Covid Resource Manager Policies. "
						+ "So It has been removed. "+"You got "+postUser.getWarnings() +
						" out of 5. After 5 warnings your account will get suspended.";

				notificationService.saveNotification(null, activityType, "post", 
									"post/" + id, postUser);
				postUser.setWarnings(postUser.getWarnings()+1);
				if(postUser.getWarnings()>5) {
					postUser.setEnabled(0);
					userService.updateUser(postUser);
				}
			}
		}
		else
			throw new IncorrectUserException("We are sorry but you do not have that permission.");
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
				
		List<Tag> associatedTags = tagService.getAllTagsByName(searchEntry);
		
		for(Tag currentTag: associatedTags) {
			Set<Post> postsWithcurrentTag = currentTag.getPosts();
			searchList.addAll(postsWithcurrentTag);		
		}

	    return searchList;
	}
	
	public void reportPost(int id) {
		User senderUser = userService.getLoggedInUser();

		Post post = postDao.findPostById(id);
		String activityType = "This post doesn't concern Covid";
		List<User> admins = userService.getAllAdmin();
		for (User user : admins) {
			notificationService.saveNotification(senderUser, activityType, "post", "post/" + id, user);
		}
		
	}
	
	public void sharePost(int postID, String username)
	{
		
		User user = userService.getLoggedInUser();
		
		if(username.equals(user.getUsername()))
		{
			//post to be shared
			Post shareThisPost = getPostById(postID);
			
			//new post instance to be added on current user's wall
			Post newPost = new Post();
			
			//copying values
			newPost.setMessage("/post/"+postID);
			newPost.setTags(new HashSet<Tag>(shareThisPost.getTags()));
			newPost.setTagStr(shareThisPost.getTagStr());
			newPost.setType(shareThisPost.getType());
			newPost.setUser(user);
			newPost.setDateTime(new Timestamp(System.currentTimeMillis()));
			
			//saving the shared post as a new post in the db
			postDao.save(newPost);
			
		}
	}
	
	
}
