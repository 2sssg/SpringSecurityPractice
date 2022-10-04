package com.example.springsecuritypractice.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class SampleService {

	public void dashboard() {
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		Object principal = authentication.getPrincipal();
//		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//		Object credentials = authentication.getCredentials();
//		boolean authenticated = authentication.isAuthenticated();

		// 직접 스레드에 등록할 필요없다
//		Account account = AccountContext.getAccount();
//		System.out.println("==============================");
//		System.out.println(account.getUsername());

		// SecurityContextHolder의 기본전략이 Thread scope이기 때문에 여기에서 꺼내서 쓸 수 있다.
		Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		System.out.println("==============================");
		System.out.println(userDetails.getUsername());
	}
}
