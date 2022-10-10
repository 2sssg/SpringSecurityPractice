package com.example.springsecuritypractice.service;

import com.example.springsecuritypractice.account.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SampleServiceTest {

	@Autowired
	AccountService accountService;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	SampleService sampleService;

	@Test
//	@WithAdmin
	public void dashboard() {
		Account account = Account.builder()
				.role("ADMIN")
				.username("seokjlee")
				.password("123")
				.build();
		accountService.createAccount(account);

		UserDetails userDetails = accountService.loadUserByUsername("seokjlee");
		UsernamePasswordAuthenticationToken token =
				new UsernamePasswordAuthenticationToken(userDetails, "123");
		Authentication authentication = authenticationManager.authenticate(token);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		sampleService.dashboard();
	}

}