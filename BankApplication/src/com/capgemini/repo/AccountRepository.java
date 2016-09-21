package com.capgemini.repo;

import com.capgemini.model.Account;

public interface AccountRepository {
	
	boolean save(Account account);
	
	Account searchAccount(int accountNumber);

}
