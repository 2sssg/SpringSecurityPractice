package com.example.springsecuritypractice.controller;

import com.example.springsecuritypractice.account.Account;
import com.example.springsecuritypractice.book.BookRepository;
import com.example.springsecuritypractice.common.CurrentUser;
import com.example.springsecuritypractice.common.SecurityLogger;
import com.example.springsecuritypractice.repository.AccountRepository;
import com.example.springsecuritypractice.service.SampleService;
import java.security.Principal;
import java.util.concurrent.Callable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SampleController {

	@Autowired SampleService sampleService;

	@Autowired AccountRepository accountRepository;

	@Autowired BookRepository bookRepository;

	@GetMapping("/")
	public String index(Model model, @CurrentUser Account account) {
		if (account == null) {
			model.addAttribute("message", "Hello, Spring Security");
		} else {
			model.addAttribute("message",
					"Welcome back, " + account.getUsername());
		}

		return ("index");
	}

	@GetMapping("/info")
	public String info(Model model) {
		model.addAttribute("message", "Here are more information");

		return ("info");
	}

	@GetMapping("/dashboard")
	public String dashboard(Model model, Principal principal) {
		model.addAttribute("message", "Hi, " + principal.getName());
		// 직접 넣어주지 않아도 스프링 시큐리티가 알아서 넣어준다.
//		AccountContext.setAccount(accountRepository.findByUsername(principal.getName()));
		sampleService.dashboard();

		return ("dashboard");
	}

	@GetMapping("/admin")
	public String admin(Model model, Principal principal) {
		model.addAttribute("message",
				"Hello Admin, " + principal.getName());
		return ("admin");
	}

	@GetMapping("/user")
	public String user(Model model, Principal principal) {
		model.addAttribute("message",
				"Hello user, " + principal.getName());
		model.addAttribute("books",
				bookRepository.findCurrentBooks());
		return ("user");
	}

	@GetMapping("/async-handler")
	@ResponseBody
	public Callable<String> asyncHandler() {
		SecurityLogger.log("MVC");
		return () -> {
			SecurityLogger.log("callable");
			return ("Async Handler");
		};
	}

	@GetMapping("/async-service")
	@ResponseBody
	public String asyncService() {
		SecurityLogger.log("MVC, before async service");
		sampleService.asyncService();
		SecurityLogger.log("MVC, after async service");

		return ("Async Service");
	}

}
