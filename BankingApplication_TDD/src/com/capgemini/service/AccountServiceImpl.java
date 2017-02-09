package com.capgemini.service;

import com.capgemini.exceptions.InsufficientBalanceException;
import com.capgemini.exceptions.InsufficientInitialAmountException;
import com.capgemini.exceptions.InvalidAccountNumberException;
import com.capgemini.model.Account;
import com.capgemini.repository.AccountRepository;

public class AccountServiceImpl implements AccountService {
	
	AccountRepository accountRepository;
	public AccountServiceImpl(AccountRepository accountRepository)
	{
		this.accountRepository=accountRepository;
	}
	
	/* (non-Javadoc)
	 * @see com.capgemini.service.AccountService#createAccount(int, int)
	 */
	@Override
	public Account createAccount(int accountNumber,int amount)throws InsufficientInitialAmountException
	{
		if(amount<500)
		{
			throw new InsufficientInitialAmountException();
		}
		Account account = new Account();
		account.setAccountNumber(accountNumber);
		account.setAmount(amount);
		
		if(accountRepository.save(account))
		{
			return account;
		}
		
		return null;
	}
	
	
	/* (non-Javadoc)
	 * @see com.capgemini.service.AccountService#depositAmount(int, int)
	 */
	@Override
	public boolean depositAmount(int accountNumber, int amount) throws InvalidAccountNumberException
	{
		if (accountNumber !=  123 ){
			throw new InvalidAccountNumberException();
		}
		else {
			accountRepository.depositAmount(accountNumber, amount);
			return true;
		}
				
	}
	
	/* (non-Javadoc)
	 * @see com.capgemini.service.AccountService#withdrawAmount(int, int)
	 */
	@Override
	public int withdrawAmount(int accountNumber,int amount)throws InvalidAccountNumberException,InsufficientBalanceException
	{
		return amount;
		
	}
	
	@Override
	public Account searchAccount (int accountNumber) throws InvalidAccountNumberException
	{
		if (accountNumber != 123456 ) {
			throw new InvalidAccountNumberException();
		}
		else {
			Account account = new Account();
			account.setAccountNumber(123456);
			account.setAmount(50);
			return account;
		}
	}

	
	
}
