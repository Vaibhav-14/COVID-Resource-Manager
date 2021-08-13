package com.mycompany.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="role_id")
	private int id;
	
	@Column
	private String name;

	public Role() {
	}

	public Role(String role) {
		this.name = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRole() {
		return name;
	}

	public void setRole(String role) {
		this.name = role;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + "]";
	}
	
	
		
}
