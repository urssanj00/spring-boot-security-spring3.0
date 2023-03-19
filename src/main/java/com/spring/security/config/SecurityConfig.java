package com.spring.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	@Bean
	public PasswordEncoder passwordEncoder () {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		System.out.println("Entering userDetailsService");
		UserDetails normalUser = User.withUsername("sanjeev")
									 .password(passwordEncoder().encode("sanjeev"))
									// .password("sanjeev")
									 .roles("NORMAL")
									 .build();
		System.out.println("userDetailsService:normalUser->"+normalUser);
		
		UserDetails adminUser  = User.withUsername("root")
									  .password(passwordEncoder().encode("root"))
									 //.password("root")
									 .roles("ADMIN")
									 .build();
		System.out.println("userDetailsService:adminUser->"+adminUser);

		InMemoryUserDetailsManager inMemoryUserDetailsManager =  new InMemoryUserDetailsManager(normalUser,adminUser );
		
		System.out.println("userDetailsService:inMemoryUserDetailsManager->"+inMemoryUserDetailsManager);

		
		return inMemoryUserDetailsManager;		
	}
	
	@Bean
	public SecurityFilterChain filterChain (HttpSecurity httpSecurity) throws Exception{
		System.out.println("Entering filterChain->"+httpSecurity);
 		httpSecurity.csrf().disable().authorizeHttpRequests()
 		.requestMatchers("/home/admin")
 		.hasRole("ADMIN")
 		.requestMatchers("/home/normal")
 		.hasRole("NORMAL") 		
 		.requestMatchers("/home/public")
 		.permitAll()
 		.anyRequest()
 		.authenticated()
 		.and()
 		.formLogin();
		System.out.println("filterChain:httpSecurity->"+httpSecurity);

 		return httpSecurity.build();
	}
}
