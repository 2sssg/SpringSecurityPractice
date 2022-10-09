package com.example.springsecuritypractice.controller;

import com.example.springsecuritypractice.account.Account;
import com.example.springsecuritypractice.dto.AccountDTO;
import com.example.springsecuritypractice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class SignUpController {

	@Autowired
	AccountService accountService;

	@GetMapping
	public String signupForm(Model model) {
		model.addAttribute("account", new Account());
		return "signup";
	}

	@PostMapping
	public String processSignUp(@ModelAttribute AccountDTO accountDTO) {
		accountDTO.setRole("USER");
		accountService.createAccount(accountDTO);
		return "redirect:/";
	}
}
