package com.example.springsecuritypractice.service;

import com.example.springsecuritypractice.common.SecurityLogger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class SampleService {

	//이 두 어노테이션은 메서드 호출 이전에 검사를한다
	// 그리고 Spring EL을 사용하지 못한다
	@Secured("ROLE_USER")
//	@RolesAllowed("ROLE_USER")

	// 얘 역시 메서드 호출 이전에 검사를하지만 Spring EL 사용가능
//	@PreAuthorize("hasRole('USER')")

	//얘는 메서드 호출이후에 추가적인 인가를 할 수 있다
//	@PostAuthorize("hasRole('USER')")
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

	@Async
	public void asyncService() {
		SecurityLogger.log("Async Service");
		System.out.println("Async service is called");
	}
}
