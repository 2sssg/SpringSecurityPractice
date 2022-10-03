package com.example.springsecuritypractice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
		httpSecurity.authorizeHttpRequests()
				.mvcMatchers("/", "/info").permitAll()
				.mvcMatchers("/admin").hasRole("ADMIN")
				.anyRequest().authenticated()
				.and().formLogin()
				.and().httpBasic();

		// chaining을 사용하지 않고 따로 명세해주어도 상관없다.
//		httpSecurity.formLogin();
//		httpSecurity.httpBasic();
		return (httpSecurity.build());
	}
}
