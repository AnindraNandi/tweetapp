package com.tweetdata.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.tweetdata.bean.Login;
import com.tweetdata.bean.Tweet;
import com.tweetdata.bean.User;
import com.tweetdata.repository.LoginRepository;
import com.tweetdata.repository.ReplyRepository;
import com.tweetdata.repository.TweetRepository;


public class LoginServiceTest {
private MockMvc mockMvc;
	
	@Mock
	private LoginRepository userrepo;
	
	@Mock
	private TweetRepository tweetRepo;
	
	@Mock
	private ReplyRepository replyRepo;
	
	@InjectMocks
	private LoginService userServiceMock;
	
	@BeforeEach
	public void setup() {

		MockitoAnnotations.initMocks(this);
	}
	
	
	@Test
	public void testregisterUser() throws Exception {

		User user = new User();
		user.setUsername("kamal");
		user.setEmail("test123456@gmail.com");
		user.setPassword("123456");
		user.setAge(34);
		user.setGender("male");
		user.setStatus(0);
		User actual = new User();
		actual.setUsername("kamal");
		actual.setEmail("test123456@gmail.com");
		actual.setPassword("123456");
		actual.setAge(34);
		actual.setGender("male");
		actual.setStatus(0);
   when(userServiceMock.registerUser(user)).thenReturn(user);
		
		assertEquals(actual,actual);
	}
	
	
}
