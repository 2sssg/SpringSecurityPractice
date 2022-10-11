package com.example.springsecuritypractice.common;

import com.example.springsecuritypractice.account.Account;
import com.example.springsecuritypractice.book.Book;
import com.example.springsecuritypractice.book.BookRepository;
import com.example.springsecuritypractice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DefaultDataGenerator implements ApplicationRunner {

	@Autowired
	AccountService accountService;

	@Autowired
	BookRepository bookRepository;


	@Override
	public void run(ApplicationArguments args) throws Exception {
		Account seokjlee = createUser("seokjlee");
		Account tempuser = createUser("temp");

		createBook(seokjlee, "spring");
		createBook(tempuser, "hibernate");

	}

	private void createBook(Account account, String bookname) {
		Book book = new Book();
		book.setAuthor(account);
		book.setTitle(bookname);
		bookRepository.save(book);
	}

	private Account createUser(String username) {
		return (accountService.createAccount(Account.builder()
				.username(username)
				.password("123")
				.role("USER")
				.build()));
	}
}
