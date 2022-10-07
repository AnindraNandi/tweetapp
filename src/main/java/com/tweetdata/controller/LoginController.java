package com.tweetdata.controller;


import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tweetdata.configuration.ProducerService;
import com.tweetdata.bean.Login;
import com.tweetdata.bean.User;

import com.tweetdata.model.JwtResponse;
import com.tweetdata.service.LoginService;
import com.tweetdata.utility.JWTUtility;

@RestController
@RequestMapping("/api/v1.0/tweets")
@CrossOrigin(origins = "http://react-anindo.s3-website-ap-northeast-1.amazonaws.com")
public class LoginController {
    @Autowired
    private JWTUtility jwtUtility;
    
	@Autowired
	ProducerService producerService;

    @Autowired
    private AuthenticationManager authenticationManager;

   
	@Autowired
	LoginService loginService;
	
	//@Autowired
	//ProducerService producerService;
	
	private static final Logger logger = LogManager.getLogger(LoginController.class);

	
	@PostMapping("/register")
	public User registerUser(@RequestBody User user) throws Exception {
	//	this.producerService.sendMessage( user.getEmail());
		return loginService.registerUser(user);
	}
	
	@PostMapping(value = "/publish")
	  public void sendMessageToKafkaTopic(@RequestParam("message") String message) {
		    logger.log(Level.INFO,message);
	       this.producerService.sendMessage(message);
	  }
	  @PostMapping("/login")
	  @CrossOrigin(origins = "http://react-anindo.s3-website-ap-northeast-1.amazonaws.com")
	    public JwtResponse authenticate(@RequestBody Login login) throws Exception{

	        try {
	            authenticationManager.authenticate(
	                    new UsernamePasswordAuthenticationToken(
	                            login.getEmail(),
	                            login.getPassword()
	                    )
	            );
	        } catch (BadCredentialsException e) {
	            throw new Exception("INVALID_CREDENTIALS", e);
	        }

	        final UserDetails userDetails
	                = loginService.loadUserByUsername(login.getEmail());

	        final String token =
	                jwtUtility.generateToken(userDetails);
	        JwtResponse jwt= new JwtResponse();
	        jwt.setJwtToken(token);

	        return  jwt;
	    }
	  
	@PostMapping("/login2")
@CrossOrigin(origins = "http://react-anindo.s3-website-ap-northeast-1.amazonaws.com")
	public User login(@RequestBody Login login) throws Exception {
		
		return loginService.login(login);
	}
	
	@PostMapping("/logout")
	public String logout(@RequestParam String email) throws Exception {
		
		return loginService.logout(email);
	}
	
	
	@GetMapping("/{email}/forgot")
	public User forgetPassword(@PathVariable String email) throws Exception {
		
		return loginService.forgetPassword(email);
	}
	
	@PutMapping("/updateUser")
	public User updateUser(@RequestBody Login login) {
		
		return loginService.updateUser(login);
	}
	
	@GetMapping("/users/all")
	public List<User> getAllUsers() {
		return loginService.getAllUsers();
	}
	
	@GetMapping("/user/search/{username}")
	public List<User> getUsersByName(@PathVariable String username) {
		
		return loginService.getUsersByName(username);
	}
	

}
