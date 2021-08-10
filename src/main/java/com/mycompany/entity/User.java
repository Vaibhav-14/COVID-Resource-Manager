package com.mycompany.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	
	@NotBlank(message ="Please enter your first name")
	private String firstname;
	
	@NotBlank(message= "Please enter your last name")
	private String lastname;
	
	@NotBlank(message= "Please enter a username")
	private String username;
	
	@NotBlank(message= "Please enter your email")
	@Email(message = "Please enter the email in the right format")
	private String email;
	
	@Size(min = 8, message = "Password Must be 8 Characters Long")
	private String password;
	
	@Transient
    private String retypepassword;
	
	@Pattern(regexp = "[0-9]{10,10}", message="Please enter a valid phone number")
	private String mobile;
	
	private int warnings;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateOfBirth;
	
	@NotNull(message = "Must Specify a Gender")
	private String gender;
	private int enabled;
	
	@ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, 
			CascadeType.REFRESH}, fetch = FetchType.EAGER)
	@JoinTable(
			name="users_roles",
			joinColumns = @JoinColumn(name="user_id"),
			inverseJoinColumns = @JoinColumn(name="role_id")
	)
	private Set<Role> roles = new HashSet<>();
	
	@OneToMany(mappedBy = "receiver", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
	private Set<Notification> notifications = new HashSet<>();
	
	@OneToMany(mappedBy = "user", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	private Set<Post> posts = new HashSet<>();
	
	@OneToMany(mappedBy = "user", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	private Set<Comment> comments = new HashSet<>();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public int getWarnings() {
		return warnings;
	}
	public void setWarnings(int warnings) {
		this.warnings = warnings;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getEnabled() {
		return enabled;
	}
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public String getRetypepassword() {
		return retypepassword;
	}
	public void setRetypepassword(String retypepassword) {
		this.retypepassword = retypepassword;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public Set<Post> getPosts() {
		return posts;
	}
	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}
	
	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public Set<Notification> getNotifications() {
		return notifications;
	}
	public void setNotifications(Set<Notification> notifications) {
		this.notifications = notifications;
	}
	public User()
	{
		
	}
	
	public User(String firstname, String lastname, String username, String email, String password,
			String mobile, int warnings, Date dateOfBirth, String gender, int enabled) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.email = email;
		this.password = password;
		this.mobile = mobile;
		this.warnings = warnings;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.enabled = enabled;
	}
	
	public boolean isEnabled() {
		if (this.enabled == 1)
			return true;
		return false;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", username=" + username
				+ ", email=" + email + ", password=" + password + ", retypepassword=" + retypepassword + ", mobile="
				+ mobile + ", warnings=" + warnings + ", dateOfBirth=" + dateOfBirth + ", gender=" + gender
				+ ", enabled=" + enabled + ", roles=" + roles + "]";
	}
	
	
}
