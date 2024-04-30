package com.vj.springboot.firstwebapp.security;

import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import  org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {
	
	@Bean
	public InMemoryUserDetailsManager createUserDetailsManager()
	{
		
	
		UserDetails userDetails1 = createNewUser("itachi", "sasuke");
		UserDetails userDetails2 = createNewUser("naruto", "hinata");
		
		
		return new InMemoryUserDetailsManager(userDetails1,userDetails2);
	}



	private UserDetails createNewUser(String username, String password) {
		Function<String, String> passwordEncoder
		= input -> passwordEncoder().encode(input);
		
		
		
		UserDetails userDetails = User.builder()
									   .passwordEncoder(passwordEncoder)
									  .username(username)
									  .password(password)
									  .roles("USER","ADMIN")
									  .build();
		return userDetails;
	}
	
	
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	
	
	// 1. all urls are protected.
	// 2. a login form is shown for unauthroized requests.
	
	
	//now to make it work the h2 console
	// csrf should be disabled.
	// h2-console uses frames and spring by default doesn't allow frames so we have to 
	// enable frames.
	
	// by default securityFilterChain only allows 1 and 2 
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
	{
		http.authorizeHttpRequests(
				auth -> auth.anyRequest().authenticated());
		
		 http.formLogin(Customizer.withDefaults());
		    http.csrf(csrf -> csrf.disable());
		    http.headers(header -> header.frameOptions(frameOptions -> frameOptions.disable()));
		 
				 
		return http.build();
		
	}
	
	
	
	
	
	
	
	

}
