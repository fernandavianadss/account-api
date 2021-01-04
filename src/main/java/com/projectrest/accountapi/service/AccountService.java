package com.projectrest.accountapi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.projectrest.accountapi.dto.AccountPost;
import com.projectrest.accountapi.dto.AccountResponse;
import com.projectrest.accountapi.dto.TransferPost;
import com.projectrest.accountapi.dto.TransferResponse;
import com.projectrest.accountapi.entity.Account;
import com.projectrest.accountapi.exception.BadRequestException;

@Service
public class AccountService {

	private List<Account> accounts = new ArrayList<>(); 

	public List<Account> listAll() {

		return accounts;
	}
	
	public void reset() {
		accounts.clear();
	}

	public Account findByAccount(String id) {

		return accounts.stream().filter(account -> account.getDestination().equals(id)).findFirst()
				.orElseThrow(() -> new BadRequestException("0"));
	}

	public AccountResponse save(Account account) {
		Account newAccount = new Account();
		newAccount.setType(account.getType());
		newAccount.setDestination(account.getDestination());
		newAccount.setAmount(account.getAmount());
		accounts.add(newAccount);
		
		//AccountPost response = new AccountPost(accountDeposit.getDestination(), accountDeposit.getAmount());
		
		return AccountResponse.toDTO(new AccountPost(newAccount.getDestination(), newAccount.getAmount()));
	}
	
	public AccountResponse deposit(Account account, String type) {

		Account accountDeposit = findByAccount(account.getDestination());
		
		accountDeposit.setType(account.getType());
		accountDeposit.setDestination(account.getDestination());
		accountDeposit.deposit(account.getAmount());
		
		accounts.remove(account);
		accounts.add(accountDeposit);
		
		//AccountPost response = new AccountPost(accountDeposit.getDestination(), accountDeposit.getAmount());
		
		return AccountResponse.toDTO(new AccountPost(accountDeposit.getDestination(), accountDeposit.getAmount()));
	}
	
	public AccountResponse whithdraw(Account account, String type) {
		
		Account accountWhithdraw = findByAccount(account.getDestination());
		
		accountWhithdraw.setType(account.getType());
		accountWhithdraw.setDestination(account.getDestination());
		accountWhithdraw.withdraw(account.getAmount());
		
		accounts.remove(account);
		accounts.add(accountWhithdraw);

		return AccountResponse.toDTO(new AccountPost(accountWhithdraw.getDestination(), accountWhithdraw.getAmount()));
	}
	
	public TransferResponse transfer(TransferPost transferPost) {
		
		Account accountOrigin = findByAccount(transferPost.getOrigin());
		Account accountDestination = findByAccount(transferPost.getDestination());
		
		accountOrigin.transfer(accountDestination, transferPost.getAmount());
		
		AccountPost response1 = new AccountPost(accountOrigin.getDestination(), accountOrigin.getAmount());
		AccountPost response2  = new AccountPost(accountDestination.getDestination(), accountDestination.getAmount());
		
		return TransferResponse.toDTO(response1, response2);
	}

}
