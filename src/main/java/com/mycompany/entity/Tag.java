package com.mycompany.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="tags")
public class Tag {
	
	@Id
	@Column
	private String name;
	
	@ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, 
			CascadeType.REFRESH})
	@JoinTable(
		name="posts_tags",
		joinColumns = @JoinColumn(name="tag_name"),
		inverseJoinColumns = @JoinColumn(name = "post_id")
	)
	private Set<Post> tags = new HashSet<>();

	public Tag() {
	}
	
	public Tag(String name, Set<Post> tags) {
		this.name = name;
		this.tags = tags;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Post> getTags() {
		return tags;
	}

	public void setTags(Set<Post> tags) {
		this.tags = tags;
	}

	@Override
	public String toString() {
		return "Tag [name=" + name + ", tags=" + tags + "]";
	}
}
