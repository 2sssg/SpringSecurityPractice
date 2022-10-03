package com.example.springsecuritypractice.config;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
		httpSecurity.authorizeHttpRequests()
				.mvcMatchers("/", "/info", "/account/**").permitAll()
				.mvcMatchers("/admin").hasRole("ADMIN")
				.anyRequest().authenticated()
				.and().formLogin()
				.and().httpBasic();

		// chaining을 사용하지 않고 따로 명세해주어도 상관없다.
//		httpSecurity.formLogin();
//		httpSecurity.httpBasic();
		return (httpSecurity.build());
	}

	//기존 방식
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication()
//				.withUser("seokjin").password("{noop}123").roles("USER")
//				.and()
//				.withUser("admin").password("{noop}!@#").roles("ADMIN");
//	}

	//바뀐 방식
//	@Bean
//	public InMemoryUserDetailsManager userDetailsService() {
//		List<UserDetails> userDetailsList = new ArrayList<>();
//		userDetailsList.add(User.builder()
//				.username("seokjin")
//				.password("{noop}123")
//				.roles("USER")
//				.build());
//		userDetailsList.add(User.builder()
//				.username("admin")
//				.password("{noop}!@#")
//				.roles("ADMIN")
//				.build());
//
//		return (new InMemoryUserDetailsManager(userDetailsList));
//	}


	// 데이터베이스 사용해서 사용자 정보 등록


}
