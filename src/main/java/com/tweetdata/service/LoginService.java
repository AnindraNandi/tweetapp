package com.tweetdata.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tweetdata.bean.Login;
import com.tweetdata.bean.User;
import com.tweetdata.repository.LoginRepository;


@Service
public class LoginService implements UserDetailsService{
	
	@Autowired
	LoginRepository loginRepository;
	
	public User registerUser(User user) throws Exception {
		
		List<User> userList = checkUser(user.getEmail());
		if(!userList.isEmpty()) {
			throw new Exception(userList.get(0).getEmail()+ " is already available in database! Please enter the new email!...");
		}
		User user1= new User();
		user1.setAge(user.getAge());
		user1.setEmail(user.getEmail());
		user1.setFirst_name(user.getFirst_name());
		user1.setGender(user.getGender());
		user1.setId(user.getId());
		user1.setLast_name(user.getLast_name());
		user1.setPassword(user.getPassword());
		user1.setStatus(user.getStatus());
		user1.setUsername(user.getUsername());
		User usr =loginRepository.save(user1);
			return usr;
	
	}
	
	public List<User> checkUser(String email) {
		
		return loginRepository.findByEmail(email);
	}

	public User login(Login login) throws Exception {
		
		
		List<User> userList =loginRepository.findByEmailAndPassword(login.getEmail(), login.getPassword());
		
		if(userList.isEmpty()) {
			throw new Exception("Please check your crdentials!");
		}
		
		userList.get(0).setStatus(1);
		
		loginRepository.save(userList.get(0));
		
		return loginRepository.save(userList.get(0));
		
	}
	
	public String logout(String email) throws Exception {
		List<User> userList = loginRepository.findByEmail(email);
		
		if(userList.isEmpty()) {
			throw new Exception("We couldn't find your email");
		}
		
		userList.get(0).setStatus(0);
		loginRepository.save(userList.get(0));
		
		return "logged out";
	}
	
	public List<User> getAllUsers(){
		
		List<User> userLists = loginRepository.findAll();
		
		return userLists;
	}
	
	public List<User> getUsersByName(String username) {
		
		List<User> userLists = loginRepository.findByUsername(username);
		
		return userLists;
	}
	
	public User forgetPassword(String email) throws Exception {
		
		List<User> userList = loginRepository.findByEmail(email);
		
		if(userList.isEmpty()) {
			throw new Exception("Please check your email!");
		}
	
		
		return userList.get(0);
	}
	
	public User updateUser(Login login) {
		
		List<User> userList = loginRepository.findByEmail(login.getEmail());
		
		userList.get(0).setPassword(login.getPassword());

		return loginRepository.save(userList.get(0));
	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        //Logic to get the user form the Database
    	List<com.tweetdata.bean.User> user=loginRepository.findByEmail(userName);
    	if(user==null)
    	{
    		throw new UsernameNotFoundException(userName);
    	}

        return new org.springframework.security.core.userdetails.User(user.get(0).getEmail(),"{noop}"+user.get(0).getPassword(),new ArrayList<>());
    }
	
	
}
