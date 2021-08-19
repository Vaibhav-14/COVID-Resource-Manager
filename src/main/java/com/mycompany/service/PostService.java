package com.mycompany.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.dao.IPostFunctionDAO;
import com.mycompany.dao.IRoleFunctionDAO;
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
	private CommentService commentService;
	
	@Autowired
	private TagService tagService;
		
	@Autowired
	private NotificationService notificationService;
	
	private static final Logger logger = LoggerFactory.getLogger(PostService.class);

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
		logger.info("User : " + loggedInUser.getUsername() + " created a post");
		String activityType = "@" + loggedInUser.getUsername() + " mentioned you in a post";

		notificationService.saveNotification(loggedInUser, activityType, "post", "post/" + post.getId(), mentionedUsers);
	}

	// retrieve tags from tagStr
	private void addTagsToPost(Post post) {
		Set<Tag> tags = new HashSet<Tag>();
		Iterable<Tag> db_tags = tagService.getAllTags();
		String tagStr = post.getTagStr();
		if (tagStr.length() > 0) {
			for (String tagString : tagStr.replaceAll("#", "").split(" ")) {
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
		}
		
		if (post.getType().equals("Available"))
			tags.add(tagService.getTagByName("Available"));
		else if (post.getType().equals("Required"))
			tags.add(tagService.getTagByName("Required"));
		post.setTags(tags); 
	}

	public Post getPostById(int id) {
		Post post;
		StringBuffer str = new StringBuffer();

		try {
			post = postDao.findById(id).get();
			for (Tag tag : post.getTags()) {
				str.append("#" + tag.getName() + " ");
				post.setTagStr(str.toString());
				sortComments(post);
			}
		} catch (Exception e) {
			post = null;
		}		
		return post;
	}
	

	public void updatePost(Post post) throws IncorrectUserException {
		Post oldPost = getPostById(post.getId());
		post.setDateTime(oldPost.getDateTime());
		addTagsToPost(post);
		User loggedInUser = userService.getLoggedInUser();

		if(post.getUser().getId() == loggedInUser.getId()) {
			postDao.save(post);
			logger.info("User : " + loggedInUser.getUsername() + " has updated post with id = " + post.getId());
		}
		else {
			logger.error("Use : " + loggedInUser.getUsername() + " doesn't have permission to update post with id = " + post.getId());
			throw new IncorrectUserException("We are sorry but you do not have that permission.");
		}

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
				logger.warn("Admin has deleted post of user : " + postUser.getUsername());
				String activityType = "Your post violets the Covid Resource Manager Policies. "
						+ "So It has been removed. "+"You got "+(postUser.getWarnings()+1) +
						" out of 5. After 5 warnings your account will get suspended.";

				notificationService.saveNotification(null, activityType, "post", 
									"/user/profile", postUser);
				postUser.setWarnings(postUser.getWarnings()+1);
				if(postUser.getWarnings()>5) {
					logger.warn("The account of user : " + postUser.getUsername() + " is suspended autometically due to 5 warnings");
					postUser.setEnabled(0);
					userService.updateUser(postUser);
				}
			}
			else {
				logger.info("User : " + postUser.getUsername() + " has deleted post with id = " + id);
			}
		}
		else {
			logger.error("User : " + user.getUsername() + " doesn't have permission to delete post with id = " + id);
			throw new IncorrectUserException("We are sorry but you do not have that permission.");
		}
	}
	
	public List<Post> getAllPost(){
		return postDao.findAllByOrderByDateTimeDesc();
	}
	
	public List<Post> getAllPostsFromPageable(int pageNumber,int pageSize,String sortBy)
	{
		Pageable paging = PageRequest.of(pageNumber, pageSize, org.springframework.data.domain.Sort.by(sortBy).descending());
		 
        Page<Post> pagedResult = postDao.findAll(paging);
         
        if(pagedResult.hasContent()) 
            return sortComments(pagedResult.getContent());
        
        return new ArrayList<Post>();
	}
	
	public List<Post> findPostByUsername(String username) {
		return sortComments(postDao.findPostByUser(userService.getUserFromUsername(username)));
	}
	
	public Set<Post> getPostOnSearch(String searchEntry) {
		Set<Post> searchList = new HashSet<>();
		
		//finding posts by username
		searchList.addAll(findPostByUsername(searchEntry));
				
		try {
			Tag associatedTag = tagService.getTagByName(searchEntry);
			
			Set<Post> postsWithcurrentTag = associatedTag.getPosts();
			searchList.addAll(postsWithcurrentTag);
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
	    return searchList;
	}
	
	public void reportPost(int id) {
		User senderUser = userService.getLoggedInUser();

		String activityType = "This post doesn't concern Covid";
		List<User> admins = userService.getAllAdmin();
		logger.info("User : " + senderUser.getUsername() + " has reported post with id = " + id);
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
			
			String referURL =   shareThisPost.getMessage();
			
			newPost.setMessage(referURL);
			newPost.setTags(new HashSet<Tag>(shareThisPost.getTags()));
			newPost.setTagStr(shareThisPost.getTagStr());
			newPost.setType(shareThisPost.getType());
			newPost.setUser(user);
			newPost.setDateTime(new Timestamp(System.currentTimeMillis()));
			
			
			//saving the shared post as a new post in the db
			postDao.save(newPost);
			
			Comment comment = new Comment();
			comment.setPost(newPost);
			comment.setContent("<a href= '/post/"+postID+"'>Go to Source Post</a>" );
			commentService.addComment(comment);
			
			
			//setting up a notification for sharing the post
			Set<User> receiver = new HashSet<User>();
			receiver.add(userService.getUserFromUsername(shareThisPost.getUser().getUsername()));
			String activityType = "@" + user.getUsername() + " shared your post";

			notificationService.saveNotification(user, activityType, "post", "post/" + postID, receiver);
			
		}
	}
	
	public List<Post> sortComments(List<Post> posts) {
		for (Post post : posts) {
			TreeSet<Comment> sortedSet = new TreeSet<Comment>(post.getComments());
			post.setComments(sortedSet.descendingSet());
		}
		return posts;
	}
	
	public Post sortComments(Post post) {
		TreeSet<Comment> sortedSet = new TreeSet<Comment>(post.getComments());
		post.setComments(sortedSet.descendingSet());
		return post;
	}
	
	
}
