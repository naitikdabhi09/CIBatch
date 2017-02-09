package com.capgemini.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.when;
import com.capgemini.exceptions.InsufficientInitialAmountException;
import com.capgemini.exceptions.InvalidAccountNumberException;
import com.capgemini.model.Account;
import com.capgemini.repository.AccountRepository;
import com.capgemini.service.AccountService;
import com.capgemini.service.AccountServiceImpl;

public class AccountTest {

	@Mock
	AccountRepository accountRepository;
	
	AccountService accountService;
	
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		accountService = new AccountServiceImpl(accountRepository);
	}
	
	
	/*
	 * create account
	 * 1.when the amount is less than 500 system should throw exception
	 * 2.when the valid info is passed account should be created successfully
	 */

	@Test(expected=com.capgemini.exceptions.InsufficientInitialAmountException.class)
    public void whenTheAmountIsLessThanFiveHundredSystemShouldThrowException() throws InsufficientInitialAmountException
    {
		accountService.createAccount(101, 400);
    }
	
	@Test
	public void whenTheValidInfoIsPassedAccountShouldBeCreatedSuccessfully() throws InsufficientInitialAmountException
	{
		Account account =new Account();
		account.setAccountNumber(101);
		account.setAmount(5000);
		
		when(accountRepository.save(account)).thenReturn(true);
		assertEquals(account,accountService.createAccount(101, 5000));
	}
	
	/*
	 * DepositAmount
	 * When There is invalid account number it should throw exceptions
	 * When the valid information passed it should deposit successfully
	 */
	@Test(expected=com.capgemini.exceptions.InvalidAccountNumberException.class)
	public void whenTheAccountNumberIsWrongItShouldThrowExceptions() throws InvalidAccountNumberException {
		accountService.depositAmount(111, 5000);
	}
	
	@Test
	public void whenAccountNumberIsCorrectItShouldRunSuccessFully() throws InvalidAccountNumberException {
		when(accountRepository.depositAmount(123, 5000)).thenReturn(true);
		assertEquals(true, accountService.depositAmount(123, 5000));
	}
	
	/* searchAccount */
	@Test(expected=com.capgemini.exceptions.InvalidAccountNumberException.class)
	public void whenAccountNumberNotFoundItShouldThrowException() throws InvalidAccountNumberException {
		Account account = new Account();
		account.setAccountNumber(123456);
		account.setAmount(50);
		when(accountRepository.searchAccount(123456)).thenReturn(account);
		accountService.searchAccount(111);
	}
	
	@Test
	public void whenAccountNumberfoundItShouldReturnAccount() throws InvalidAccountNumberException {
		Account account = new Account();
		account.setAccountNumber(123456);
		account.setAmount(50);
		when(accountRepository.searchAccount(123456)).thenReturn(account);
		assertEquals(account,accountService.searchAccount(123456));
	}
	
}
