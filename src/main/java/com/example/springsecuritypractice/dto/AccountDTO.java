package com.example.springsecuritypractice.dto;

import com.example.springsecuritypractice.account.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {

	private Integer id;

	private String username;

	private String password;

	private String role;

	public AccountDTO encodePassword(PasswordEncoder passwordEncoder) {
		this.password = passwordEncoder.encode(this.password);
		return this;
	}

	public Account toEntity() {
		return (Account.builder()
				.username(this.username)
				.password(this.password)
				.role(this.role)
				.build());
	}
}
