package com.example.springsecuritypractice.account;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.springsecuritypractice.service.AccountService;
import javax.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AccountControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	AccountService accountService;

	@Test
	@WithAnonymousUser
	public void index_anonymous() throws Exception {
		mockMvc.perform(get("/"))
				.andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	@WithUser
	public void index_user() throws Exception {
		mockMvc.perform(get("/"))
				.andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	@WithMockUser(username = "admin", roles = "ADMIN")
	public void admin_user() throws Exception {
		mockMvc.perform(get("/admin"))
				.andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	@Transactional
	public void login_success() throws Exception {
		String username = "seokjlee";
		String password = "123";
		String role = "USER";
		Account user = createTestUser(username, password, role);
		mockMvc.perform(formLogin().user(user.getUsername()).password("123"))
				.andExpect(authenticated());
	}

	@Test
	@Transactional
	public void login_success2() throws Exception {
		String username = "seokjlee";
		String password = "123";
		String role = "USER";
		Account user = createTestUser(username, password, role);
		mockMvc.perform(formLogin().user(user.getUsername()).password("123"))
				.andExpect(authenticated());
	}

	@Test
	@Transactional
	public void login_fail() throws Exception {
		String username = "seokjlee";
		String password = "123";
		String role = "USER";
		Account user = createTestUser(username, password, role);
		mockMvc.perform(formLogin().user(user.getUsername()).password("12345"))
				.andExpect(unauthenticated());
	}

	private Account createTestUser(String username, String password, String role) {
		Account account = Account.builder()
				.username(username)
				.password(password)
				.role(role)
				.build();
		return (accountService.createAccount(account));
	}
}
