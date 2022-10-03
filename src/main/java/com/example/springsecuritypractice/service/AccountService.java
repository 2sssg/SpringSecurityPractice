package com.example.springsecuritypractice.service;

import com.example.springsecuritypractice.account.Account;
import com.example.springsecuritypractice.dto.AccountDTO;
import com.example.springsecuritypractice.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements UserDetailsService {

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = accountRepository.findByUsername(username);
		if (account == null) {
			throw new UsernameNotFoundException(username);
		}

		return (User.builder()
				.username(account.getUsername())
				.password(account.getPassword())
				.roles(account.getRole())
				.build());
	}

	public Account createAccount(AccountDTO accountDTO) {

		return (accountRepository.save(accountDTO
				.encodePassword(passwordEncoder)
				.toEntity()));
	}

	public Account createAccount(Account account) {

		return (accountRepository.save(AccountDTO.builder()
				.id(account.getId())
				.username(account.getUsername())
				.password(account.getPassword())
				.role(account.getRole())
				.build()
				.encodePassword(passwordEncoder)
				.toEntity()));
	}
}
