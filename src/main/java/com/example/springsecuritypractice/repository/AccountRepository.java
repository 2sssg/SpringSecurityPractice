package com.example.springsecuritypractice.repository;

import com.example.springsecuritypractice.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {
	Account findByUsername(String username);
}
