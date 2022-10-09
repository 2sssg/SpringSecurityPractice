package com.example.springsecuritypractice.handler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.access.AccessDeniedHandler;

public class AdminAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		UserDetails principal = (UserDetails) SecurityContextHolder
				.getContext()
				.getAuthentication()
				.getPrincipal();
		String username = principal.getUsername();
		//로거로 찍는거 추천 아니 필수임 ㅋㅋ
		System.out.println(username + " is denied to access " + request.getRequestURI());
		response.sendRedirect("access-denied");
	}
}
