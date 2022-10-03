package com.example.springsecuritypractice.dto;

import com.example.springsecuritypractice.account.Account;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDTO {

	private Integer id;

	private String username;

	private String password;

	private String role;

	public void encodePassword(String s) {
		this.password = "{" + s + "}" + this.password;
	}

	public Account toEntity() {
		return (Account.builder()
				.username(this.username)
				.password(this.password)
				.role(this.role)
				.build());
	}
}
