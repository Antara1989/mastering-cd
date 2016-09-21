package com.capgemini.service;

import com.capgemini.exception.InsufficientBalanceException;
import com.capgemini.exception.InsufficientInitialBalanceException;
import com.capgemini.exception.InvalidAccountNumberException;
import com.capgemini.model.Account;
import com.capgemini.repo.AccountRepository;

public class AccountServiceImpl implements AccountService {

	AccountRepository accountRepository;
	
	public AccountServiceImpl(AccountRepository accountRepository)
	{
		this.accountRepository=accountRepository;
	}
	public Account createAccount(int accountNumber, int amount) throws InsufficientInitialBalanceException {
		if(amount<500)
		{
			throw new InsufficientInitialBalanceException();
		}
		
		Account account = new Account();
		
		account.setAccountNumber(accountNumber);
		account.setAmount(amount);
		
		if(accountRepository.save(account)){
			return account;
		}
		return null;
	}

	public int showBalance(int accountNumber) throws InvalidAccountNumberException {

		if(accountNumber>0){
			
			Account account=accountRepository.searchAccount(accountNumber);
			return account.getAmount();
			
		}else{
			throw new InvalidAccountNumberException(accountNumber +"Invalid account number");
		}
	}

	public int depositAmount(int accountNumber, int amount) throws InvalidAccountNumberException {
		if(accountNumber>0 && amount >0){
			 Account account=accountRepository.searchAccount(accountNumber);
			 int totalAmount=account.getAmount()+amount;
			 account.setAmount(totalAmount);
			 return account.getAmount();
		}else {
			throw new InvalidAccountNumberException(accountNumber +"Invalid account number");
		}
	}

	public int withdrawAmount(int accountNumber, int amount)
			throws InvalidAccountNumberException, InsufficientBalanceException {
		if(accountNumber>0 ){
			 if (amount >0){
				 Account account=accountRepository.searchAccount(accountNumber);
				 int totalAmount=account.getAmount()-amount;
				 account.setAmount(totalAmount);
				 return account.getAmount();
			 }else{
				 throw new InsufficientBalanceException();
			 }
			
		}else {
			throw new InvalidAccountNumberException(accountNumber +"Invalid account number");
		}
		
	}

}
