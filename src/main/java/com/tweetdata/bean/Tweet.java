package com.tweetdata.bean;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
@Entity
public class Tweet {
	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "tweet_id")
	private int id;
  private String username;
  private String email;
  private String tweets;
  private String date;
  @Column(name = "likes")
  private int like;
  private int dislike;
	/*
	 * @Column
	 * 
	 * @ElementCollection(targetClass=String.class) private List<String> replies;
	 */
 
  @OneToMany(targetEntity = Reply.class,cascade = CascadeType.ALL)
  @JoinColumn(name ="cf_fk",referencedColumnName = "tweet_id")
 // @OneToMany(mappedBy="tweet",cascade = CascadeType.ALL)
  private List<Reply> replies;

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

public String getTweets() {
	return tweets;
}

public void setTweets(String tweets) {
	this.tweets = tweets;
}

public String getDate() {
	return date;
}

public void setDate(String date) {
	this.date = date;
}

public int getLike() {
	return like;
}

public void setLike(int like) {
	this.like = like;
}

public int getDislike() {
	return dislike;
}

public void setDislike(int dislike) {
	this.dislike = dislike;
}

public List<Reply> getReplies() {
	return replies;
}

public void setReplies(List<Reply> replies) {
	this.replies = replies;
}
}
