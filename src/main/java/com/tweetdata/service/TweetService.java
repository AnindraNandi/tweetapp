package com.tweetdata.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetdata.bean.Reply;
import com.tweetdata.bean.ReplyDto;
import com.tweetdata.bean.Tweet;
import com.tweetdata.bean.User;
import com.tweetdata.repository.LoginRepository;
import com.tweetdata.repository.TweetRepository;




@Service
public class TweetService {
	
	@Autowired
	public
	TweetRepository tweetRepository;
	
	@Autowired
	LoginRepository loginRepository;
	
//	@Autowired
	//ProducerService producerService;
	
	public List<Tweet> getAllTweets() {
		
		return tweetRepository.findAll();
	}
	
	public List<Tweet> getAllTweetsByUser(String email) {
		
		return tweetRepository.findByEmail(email);
	}
	public Tweet findByEmailAndId(String email,int id)
	{
		return tweetRepository.findByEmailAndId(email, id);
	}
	
	public String postTweet(String email,String tweetDesc) {
	
		
		tweetDesc = tweetDesc.replace("=", "");
		
		if(tweetDesc.contains("+"))
		{
			tweetDesc = tweetDesc.replace("+", " ");
		}
		System.out.print("ck "+tweetDesc);
		List<User> userList = loginRepository.findByEmail(email);
		
		Tweet tweet = new Tweet();
		tweet.setUsername(userList.get(0).getUsername());
		tweet.setEmail(userList.get(0).getEmail());
		tweet.setTweets(tweetDesc);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now(); 
		tweet.setDate(dtf.format(now));
		tweet.setLike(0);
		tweet.setDislike(0);
		List<Reply> replies = new ArrayList<Reply>();
		tweet.setReplies(replies);
	//	Tweet tweets = tweetRepository.save(tweet);
		//String replies = null;
	//tweet.setReplies(replies);
		Tweet tweets = tweetRepository.save(tweet);
		
		return "Success";
	}
	
	ArrayList<String> email1 = new ArrayList<String>();
	public int likeTweet(String email,int id) {
		
		Optional<Tweet> tweet = tweetRepository.findById(id);
		
		
		if(email1.contains(email))
		{	
			if(tweet.isPresent()) {
			tweet.get().setLike(tweet.get().getLike() - 1);
		email1.remove(email);
			}
			
			}
		else {
			if(tweet.isPresent()) {
		if (tweet.get().getLike()!= 0 || tweet.get().getLike()==0) {
			tweet.get().setLike(tweet.get().getLike() + 1);
			email1.add(email);
			
		}
			}
		}
		if(tweet.isPresent()) {
		tweetRepository.save(tweet.get());
		}
		int value=0;
		System.out.println(email);
		if(tweet.isPresent()) {
		 value= tweet.get().getLike();
		}
		
		return value;
	}
	
	

	
	public Tweet updateTweet(String email,int id,String tweetDesc) {
		
		
		tweetDesc = tweetDesc.replace("\"","");
		System.out.println(tweetDesc);
		Tweet tweet =tweetRepository.findByEmailAndId(email, id);
		
		tweet.setTweets(tweetDesc);
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now(); 
		tweet.setDate(dtf.format(now));
		
		tweetRepository.save(tweet);
		
		return tweet;
		
	}
	
	public String deleteTweet(String email,int id) {
		Tweet tweet =tweetRepository.findByEmailAndId(email, id);
  //  int i=Integer.parseInt(id);
    tweetRepository.deleteById(id);
		
		//tweetRepository.delete(tweet);
	
		
		return "deleted";
	}
	public Tweet replyToTweet(ReplyDto reply, String email,int id) {
		// TODO Auto-generated method stub
		//TweetResponse response = new TweetResponse();
		//TweetsDto dto = request.getTweet();
		List<Reply> replies = new ArrayList<>();
		
			Optional<Tweet> entity = tweetRepository.findById(id);
			if (entity.isPresent()) {
				replies.addAll(entity.get().getReplies());
				entity.get().setReplies(replies);
			} else {
				throw new NullPointerException();
			}
			Reply newReplies= new Reply();
			if (reply.getReply() != null) {
				newReplies.setEmail(email);
				//newReplies.setTweet(entity.get().getTweets());
				newReplies.setUsername(reply.getUsername());
				newReplies.setReply(reply.getReply()); 
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
				LocalDateTime now = LocalDateTime.now(); 
				newReplies.setDate(dtf.format(now));
			
				replies.add(newReplies);
				entity.get().setReplies(replies);
			} else {
				throw new NullPointerException();
			}
		//	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	//		LocalDateTime now = LocalDateTime.now(); 
		//	replies.forEach(reply -> {
			//	reply.setDate(dtf.format(now));
	//		});
		
			entity.get().setReplies(replies);
		//	Tweet tweet=tweetRepository.save(entity);
			
		 
		return tweetRepository.save(entity.get());
	}

	public Tweet replyTweet(String email,int id,ReplyDto reply) {
		Tweet tweet =tweetRepository.findByEmailAndId(email, id);
		
		System.out.println("Tweet "+tweet);
		List<Reply> replies = tweet.getReplies();
        
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now(); 
	//	reply.setDate(dtf.format(now));
		System.out.println("hi"+reply);
		Reply reply1= new Reply();
		reply1.setDate(reply.getDate());
		reply1.setEmail(reply.getEmail());
		reply1.setId(reply.getId());
		reply1.setReply(reply.getReply());
		reply1.setUsername(reply.getUsername());
		replies.add(reply1);
		
		tweet.setReplies(replies);
		System.out.println("hi"+reply+tweet);
		return tweetRepository.save(tweet);
		
	}
	
	
	
	
}
