package com.tweetdata.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tweetdata.bean.User;



@Repository
public interface LoginRepository extends JpaRepository<User,Integer>{
	
	List<User> findByEmail(String email);
	
	List<User> findByUsername(String name);
	
	List<User> findByEmailAndPassword(String email,String password);

}
