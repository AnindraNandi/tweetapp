package com.tweetdata.bean;

import javax.validation.constraints.Size;

public class ReplyDto {
	private int id;
	private String username;
	private String email;
	
	@Size(min = 1, max = 150, message = "The tweet description must be between {min} and {max} characters long")
	private String reply;
	private String date;
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
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
}
