package com.example.springsecuritypractice.controller;

import com.example.springsecuritypractice.repository.AccountRepository;
import com.example.springsecuritypractice.service.SampleService;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SampleController {

	@Autowired SampleService sampleService;

	@Autowired AccountRepository accountRepository;

	@GetMapping("/")
	public String index(Model model, Principal principal) {
		if (principal == null) {
			model.addAttribute("message", "Hello, Spring Security");
		} else {
			model.addAttribute("message", "Welcome back, " + principal.getName());
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
		model.addAttribute("message", "Hello Admin, " + principal.getName());
		return ("admin");
	}

	@GetMapping("/user")
	public String user(Model model, Principal principal) {
		model.addAttribute("message", "Hello user, " + principal.getName());
		return ("user");
	}
}
