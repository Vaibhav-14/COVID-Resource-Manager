package com.mycompany.entity;

public class PostTag {
	private Post post = new Post();
	private String tags = "";
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public PostTag() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PostTag(Post post, String tags) {
		super();
		this.post = post;
		this.tags = tags;
	}
	@Override
	public String toString() {
		return "PostTag [post=" + post + ", tags=" + tags + "]";
	}

}
