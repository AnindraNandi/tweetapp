package com.tweetdata.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tweetdata.bean.Tweet;


@Repository
public interface TweetRepository extends JpaRepository<Tweet,Integer> {
	
	List<Tweet> findByEmail(String email);
	
	Tweet findByEmailAndId(String email,int id);
	
	Optional<Tweet> findById(int id);
	

	Tweet save(Optional<Tweet> tweet);

}
