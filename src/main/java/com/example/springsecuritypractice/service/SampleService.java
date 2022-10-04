package com.example.springsecuritypractice.service;

import com.example.springsecuritypractice.account.Account;
import com.example.springsecuritypractice.context.AccountContext;
import org.springframework.stereotype.Service;

@Service
public class SampleService {

	public void dashboard() {
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		Object principal = authentication.getPrincipal();
//		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//		Object credentials = authentication.getCredentials();
//		boolean authenticated = authentication.isAuthenticated();

		Account account = AccountContext.getAccount();
		System.out.println("==============================");
		System.out.println(account.getUsername());
	}
}
