package com.tweetdata.configuration;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.tweetdata.filter.JwtFilter;
import com.tweetdata.repository.LoginRepository;
import com.tweetdata.service.LoginService;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

//import com.tweetdata.service.UserService;

@Configuration
@EnableWebSecurity
@EnableAutoConfiguration
@EnableSwagger2
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

  //  @Autowired
    //private UserService userService;
@Autowired
private LoginService loginService;
   
    @Autowired
    private LoginRepository repository;

    @Autowired
    private JwtFilter jwtFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	
    	
    	//auth.inMemoryAuthentication().withUser(login.getEmail()).password(login.getPassword()).roles("USER");

        auth.userDetailsService(loginService);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    //	 web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui",
		//  "/swagger-resources/**", "/configuration/security", "/swagger-ui.html",
		//  "/webjars/**");
		 
        http.csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/v2/api-docs", "/configuration/ui",  "/v3/api-docs/**",
                		"https://cros-everywhere.herokuapp.com/http://tweetapp-env.eba-au9yqf7p.ap-northeast-1.elasticbeanstalk.com/api/v1.0/tweets/register",
                		"https://cros-everywhere.herokuapp.com/http://tweetapp-env.eba-au9yqf7p.ap-northeast-1.elasticbeanstalk.com/api/v1.0/tweets/login",
                		"http://tweetapp-env.eba-au9yqf7p.ap-northeast-1.elasticbeanstalk.com",
                		"/api/v1.0/tweets/{email}/reply3/{id}","/api/v1.0/tweets/users/all",
                		"/api/v1.0/tweets/{email}/like/{id}","/api/v1.0/tweets/{email}/reply/{id}",
                		"/api/v1.0/tweets/{email}/update/{id}","/api/v1.0/tweets/{email}/delete/{id}",
                		"/api/v1.0/tweets/usersall","/api/v1.0/tweets/user/search/{username}","/api/v1.0/tweets/{email}",
                        "/swagger-ui/**","/api/v1.0/tweets/{email}/add","/api/v1.0/tweets/login2","/api/v1.0/tweets/all","/api/v1.0/tweets/{email}/forgot","/api/v1.0/tweets/updateUser",
              		  "/swagger-resources/**", "/configuration/security", "/swagger-ui.html","/api/v1.0/tweets/register","/api/v1.0/tweets/login","/api/v1.0/tweets/authenticate")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
               
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

    }
    
    
}
