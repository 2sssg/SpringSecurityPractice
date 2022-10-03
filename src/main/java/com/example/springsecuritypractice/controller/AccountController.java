package com.example.springsecuritypractice.controller;

import com.example.springsecuritypractice.account.Account;
import com.example.springsecuritypractice.dto.AccountDTO;
import com.example.springsecuritypractice.repository.AccountRepository;
import com.example.springsecuritypractice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

	@Autowired
	AccountService accountService;

	@GetMapping("/account/{role}/{username}/{password}")
	public Account createAccount(@ModelAttribute AccountDTO accountDTO) {

		return (accountService.createAccount(accountDTO));
	}

}
