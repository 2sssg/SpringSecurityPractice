package com.example.springsecuritypractice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class AnotherSecurityConfig {
//
//	/**
//	 * Filter 예제
//	 * 여기는 전부다 비허용 (인증을 필요로 함)
//	 */
//	@Bean
//	// 얘가 우선순위가 높다
//	@Order(Ordered.LOWEST_PRECEDENCE - 15)
//	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
//		httpSecurity.antMatcher("/account/**")
//				.authorizeHttpRequests()
//				.anyRequest().authenticated()
//				.and().formLogin()
//				.and().httpBasic();
//
//		return (httpSecurity.build());
//	}
}
