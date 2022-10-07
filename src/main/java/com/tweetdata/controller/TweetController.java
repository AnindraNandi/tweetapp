package com.tweetdata.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tweetdata.bean.Reply;
import com.tweetdata.bean.ReplyDto;
import com.tweetdata.bean.Tweet;
import com.tweetdata.service.TweetService;

@RestController
@RequestMapping("/api/v1.0/tweets")
@CrossOrigin(origins = "http://react-anindo.s3-website-ap-northeast-1.amazonaws.com")
public class TweetController {
	
	
	@Autowired
	TweetService tweetService;
	
	
	@GetMapping("/all")
	public List<Tweet> getAllTweets() {
		return tweetService.getAllTweets();
	}

	
	@GetMapping("/{email}")
	public List<Tweet> getAllTweetsByUser(@PathVariable String email) {
		
		return tweetService.getAllTweetsByUser(email);
		
	}
	@PostMapping("/{email}/add")
	public ResponseEntity<String> postTweet(@PathVariable String email,@RequestBody String tweets) {
		
        List<Tweet> tweet= tweetService.getAllTweetsByUser(email);
        if(tweet==null)
        {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
		return new ResponseEntity<>(tweetService.postTweet(email,tweets), HttpStatus.OK);
	
	}
	
	@PutMapping("/{email}/update/{id}")
	public Tweet updateTweet(@PathVariable String email,@PathVariable Integer id,@RequestBody String tweets) {
		return  tweetService.updateTweet(email, id, tweets);
	}
	
	@DeleteMapping("/{email}/delete/{id}")
	public String deleteTweet(@PathVariable String email,@PathVariable Integer id) {
		
		return  tweetService.deleteTweet(email, id);
	}
	
	@PutMapping("/{email}/like/{id}")
	public int likeTweet(@PathVariable String email,@PathVariable Integer id) {
		return  tweetService.likeTweet(email, id);
	}
	
	
	
	@PostMapping("/{email}/reply/{id}")
	public ResponseEntity<Tweet> replyTweet(@PathVariable String email,@PathVariable int id ,@RequestBody ReplyDto reply) {
		Tweet tweet= tweetService.findByEmailAndId(email,id);
        if(tweet==null)
        {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        Tweet tweet2=tweetService.replyTweet(email, id, reply);
		return new ResponseEntity<Tweet>(tweet2, HttpStatus.OK);
	}
	@PostMapping("/{email}/reply3/{id}")
	public ResponseEntity<Tweet> replyTweet3(@PathVariable String email,@PathVariable int id ,@RequestBody ReplyDto reply) {
		List<Tweet> tweet= tweetService.getAllTweetsByUser(email);
        if(tweet==null)
        {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        Tweet tweet2=tweetService.replyToTweet( reply,email,id);
		return new ResponseEntity<Tweet>(tweet2, HttpStatus.OK);
	}
	
	

}
