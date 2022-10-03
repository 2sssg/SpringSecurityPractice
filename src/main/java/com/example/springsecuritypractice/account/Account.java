package com.example.springsecuritypractice.account;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Account {

	@Id @GeneratedValue
	private Integer id;

	@Column(unique = true)
	private String username;

	private String password;

	private String role;

}
