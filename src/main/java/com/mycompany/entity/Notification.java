package com.mycompany.entity;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="notifications")
public class Notification {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, 
			CascadeType.REFRESH})
	@JoinColumn(name="receiver_id")
	private User receiver;
	
	@Column(name="sender_id")
	private Integer senderId;
	
	@Column(name="activity_type")
	private String activityType;
	
	@Column(name="object_type")
	private String objectType;
	
	@Column(name="object_url")
	private String objectURL;
	
	@Column(name="time_sent")
	private Timestamp timeSent = new Timestamp(System.currentTimeMillis());
	
	@Column(name="is_unread")
	private int isUnread = 1;

	public Notification() {
	}

	public Notification(Integer senderId, String activityType, String objectType, String objectURL) {
		this.senderId = senderId;
		this.activityType = activityType;
		this.objectType = objectType;
		this.objectURL = objectURL;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	public Integer getSenderId() {
		return senderId;
	}

	public void setSenderId(Integer senderId) {
		this.senderId = senderId;
	}

	public String getActivityType() {
		return activityType;
	}

	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}

	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public String getObjectURL() {
		return objectURL;
	}

	public void setObjectURL(String objectURL) {
		this.objectURL = objectURL;
	}

	public Timestamp getTimeSent() {
		return timeSent;
	}

	public void setTimeSent(Timestamp timeSent) {
		this.timeSent = timeSent;
	}

	public int getIsUnread() {
		return isUnread;
	}

	public void setIsUnread(int isUnread) {
		this.isUnread = isUnread;
	}
	
}
