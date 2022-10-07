package com.tweetdata.bean;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Login {
	@Email(message="Email is not valid", regexp="^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")
	private String email;
	@Size(min=5,max=15, message="password must be between {min} and {max} characters long")
	@NotBlank(message = "Password is mandatory")
	private String password;
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

	/*
	 * public Login(String email, String password) { super(); this.email = email;
	 * this.password = password; }
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
