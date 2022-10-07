package com.tweetdata;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tweetdata.bean.Login;
import com.tweetdata.bean.User;
import com.tweetdata.controller.LoginController;


@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerTest {
	

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private LoginController loginController;
	int i=0;
	String email="shivam"+i+"gmail.com";
	/*
	@Test
	public void testregisterUser() throws Exception {
       int i=1;
		User user = new User();
		user.setUsername("shivam");
		//user.setEmail("shivam"+i+5+"@gmail.com");
		user.setPassword("shivam");
		user.setAge(34);
		user.setGender("male");
		user.setStatus(0);
		user.setFirst_name("shivam");
		
		user.setLast_name("khurana");
		this.mockMvc.perform(post("/api/v1.0/tweets/register").content(asJsonString(user))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isInternalServerError());
	}
	
	@Test
	public void login() throws Exception {
		
		Login login = new Login();
		login.setEmail("test123456@gmail.com");
		login.setPassword("shivam");
		
		this.mockMvc.perform(post("/api/v1.0/tweets/login").content(asJsonString(login))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
	}
	
	@Test
	public void login2() throws Exception {
		
		Login login = new Login();
		login.setEmail("test123456@gmail.com");
		login.setPassword("shivam");
		
		this.mockMvc.perform(post("/api/v1.0/tweets/login2").content(asJsonString(login))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void logout() throws Exception {
		
		this.mockMvc.perform(post("/api/v1.0/tweets/logout?email=shivam1@gmail.com"))
		.andDo(print()).andExpect(status().isOk());
	}
	
	@Test
	public void forgetPassword() throws Exception {
		this.mockMvc.perform(get("/api/v1.0/tweets/shivam1@gmail.com/forgot")).andDo(print()).andExpect(status().isOk());
	}
	
	@Test
	public void updateUser() throws Exception {
		
		Login login = new Login();
		login.setEmail("shivam1@gmail.com");
		login.setPassword("shivam");
		
		this.mockMvc.perform(put("/api/v1.0/tweets/updateUser").content(asJsonString(login))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
	}*/
	
	@Test
	public void getAllUsers() throws Exception {
		this.mockMvc.perform(get("/api/v1.0/tweets/users/all")).andDo(print()).andExpect(status().isOk());
	}
	
	@Test
	public void getUsersByName() throws Exception {
		this.mockMvc.perform(get("/api/v1.0/tweets/user/search/test@gmail.com")).andDo(print()).andExpect(status().isOk());
	}
	
	public static String asJsonString(final Object obj) throws JsonProcessingException {
                  return new ObjectMapper().writeValueAsString(obj);
      
	

}
}
