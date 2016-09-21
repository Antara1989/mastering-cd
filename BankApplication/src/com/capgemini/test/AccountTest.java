package com.capgemini.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.times;

import com.capgemini.exception.InsufficientInitialBalanceException;
import com.capgemini.model.Account;
import com.capgemini.repo.AccountRepository;
import com.capgemini.service.AccountService;
import com.capgemini.service.AccountServiceImpl;
public class AccountTest {

	AccountService accountService ;
	
	@Mock
	AccountRepository accountRepository;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		accountService = new AccountServiceImpl(accountRepository);
	}
/**
 * 1.when the amount is less than 500 exception should be thrown	
 * 2.when valid info is passed account should be created
 * @throws InsufficientInitialBalanceException 
 */
	
	@Test(expected=com.capgemini.exception.InsufficientInitialBalanceException.class)
	public void whenTheAmountIsLessThan500SystemShouldThrowException() throws InsufficientInitialBalanceException {
		accountService.createAccount(110,300);
	}
	
	
	@Test
	public void whenTheValidInfoIsPassedAccountShouldBeCreatedSuccessfully() throws InsufficientInitialBalanceException
	{
		Account account = new Account();
		account.setAccountNumber(101);
		account.setAmount(5000);
		
		Mockito.when(accountRepository.save(account)).thenReturn(true);
		
		assertEquals(account,accountService.createAccount(101, 5000));
		
		Mockito.verify(accountRepository , times(1)).save((Account)Mockito.anyObject());
		
	}
	
	
	
	
	
	
	

}
