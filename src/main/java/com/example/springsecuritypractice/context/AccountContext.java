package com.example.springsecuritypractice.context;

import com.example.springsecuritypractice.account.Account;

public class AccountContext {

	private static final ThreadLocal<Account> ACCOUNT_THREAD_LOCAL = new ThreadLocal<>();

	public static void setAccount(Account account) {
		ACCOUNT_THREAD_LOCAL.set(account);
	}

	public static Account getAccount() throws Exception {
		Account session = ACCOUNT_THREAD_LOCAL.get();
		if (session != null)
			return (session);
		throw new Exception("User is not authenticated");
	}

	public void unload() {
		ACCOUNT_THREAD_LOCAL.remove();
	}
}
