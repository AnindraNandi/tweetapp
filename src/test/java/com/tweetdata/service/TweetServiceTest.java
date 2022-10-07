package com.tweetdata.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.tweetdata.bean.Login;
import com.tweetdata.bean.Tweet;
import com.tweetdata.bean.User;
import com.tweetdata.controller.LoginController;
import com.tweetdata.repository.LoginRepository;
import com.tweetdata.repository.TweetRepository;


@SpringBootTest
@AutoConfigureMockMvc
public class TweetServiceTest {
	

	@Autowired
	private MockMvc mockMvc;
	
	@Mock
	private LoginRepository userrepo;
	
	@Mock
	private TweetRepository tweetRepo;
	
	@InjectMocks
	private TweetService tweetMock;
	
	/*
	 * @Before public void setUp() { MockitoAnnotations.initMocks(this);
	 * 
	 * // ReflectionTestUtils.setField(tweetMock, "userrepo", userrepo); //
	 * ReflectionTestUtils.setField(tweetMock, "tweetRepo", tweetRepo); }
	 */
	


@Test	
public void testupdateTweet() throws Exception
{
	Tweet tweet= new Tweet();
	tweet.setEmail("shivam1@gmail.com");
	tweet.setId(6);
	tweet.setTweets("shivam1");
	tweetMock.tweetRepository.findByEmailAndId(tweet.getEmail(), tweet.getId());
	tweet.setTweets("shivam");
	assertEquals("shivam",tweet.getTweets());
	

}
}
