package com.example.springsecuritypractice.common;

import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityLogger {

	public static void log(String message) {
		System.out.println(message);
		Thread thread = Thread.currentThread();
		System.out.println("thread name : " + thread.getName());
		var principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println("principal : " + principal);
	}
}
