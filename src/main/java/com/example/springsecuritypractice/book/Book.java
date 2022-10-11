package com.example.springsecuritypractice.book;

import com.example.springsecuritypractice.account.Account;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {

	@Id @GeneratedValue
	private Integer id;

	private String title;

	@ManyToOne
	private Account author;

}
