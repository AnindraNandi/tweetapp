package com.tweetdata.configuration;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SpringFoxConfig {    
	  private ApiKey apiKey() { 
	        return new ApiKey("JWT", "Authorization", "header"); 
	    }
	    private List<SecurityContext> securityContexts() { 
	        return Arrays.asList(SecurityContext.builder().securityReferences(defaultAuth()).build()); 
	    } 

	    private List<SecurityReference> defaultAuth() { 
	        AuthorizationScope scope = new AuthorizationScope("global", "accessEverything"); 
	      //  AuthorizationScope[] authorizationScopes = new AuthorizationScope[1]; 
	        //authorizationScopes[0] = authorizationScope; 
	        return Arrays.asList(new SecurityReference("JWT", new  AuthorizationScope[] {scope})); 
	    }
	    
	    private ApiInfo apiInfo() {
	        return new ApiInfo(
	          "My REST API",
	          "Some custom description of API.",
	          "1.0",
	          "Terms of service",
	          new Contact("Sallo Szrajbman", "www.baeldung.com", "salloszraj@gmail.com"),
	          "License of API",
	          "API license URL",
	          Collections.emptyList());
	    }
    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
        		 .apiInfo(apiInfo())
        	      .securityContexts(securityContexts())
        	      .securitySchemes(Arrays.asList(apiKey()))
          .select()                                  
          .apis(RequestHandlerSelectors.any())              
          .paths(PathSelectors.any())                          
          .build();                                           
    }
}