package com.projectrest.accountapi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.projectrest.accountapi.dto.AccountPost;
import com.projectrest.accountapi.dto.AccountResponse;
import com.projectrest.accountapi.dto.TransferPost;
import com.projectrest.accountapi.dto.TransferResponse;
import com.projectrest.accountapi.dto.WithDrawPost;
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
		
		return AccountResponse.toDTO(new AccountPost(newAccount.getDestination(), newAccount.getAmount()));
	}
	
	public AccountResponse deposit(Account account, String type) {

		Account accountDeposit = findByAccount(account.getDestination());
		int index = accounts.indexOf(accountDeposit);
		
		accountDeposit.setType(account.getType());
		accountDeposit.setDestination(account.getDestination());
		accountDeposit.deposit(account.getAmount());
		
		accounts.add(index, accountDeposit);
		
		return AccountResponse.toDTO(new AccountPost(accountDeposit.getDestination(), accountDeposit.getAmount()));
	}
	
	public AccountResponse whithdraw(WithDrawPost account, String type) {
		
		Account accountWhithdraw = findByAccount(account.getOrigin());
		
		int index = accounts.indexOf(accountWhithdraw);
		
		accountWhithdraw.setType(account.getType());
		accountWhithdraw.setDestination(account.getOrigin());
		accountWhithdraw.withdraw(account.getAmount());
		
		accounts.add(index, accountWhithdraw);

		return AccountResponse.toDTO(new AccountPost(accountWhithdraw.getDestination(), accountWhithdraw.getAmount()));
	}
	
	public TransferResponse transfer(TransferPost transferPost) {
		
		Account accountOrigin = findByAccount(transferPost.getOrigin());
		int indexAccountOrigin = accounts.indexOf(accountOrigin);
		
		Account accountDestination = findByAccount(transferPost.getDestination());
		int indexAccountDestination = accounts.indexOf(accountDestination);
		
		accountOrigin.transfer(accountDestination, transferPost.getAmount());
		
		AccountPost response1 = new AccountPost(accountOrigin.getDestination(), accountOrigin.getAmount());
		AccountPost response2  = new AccountPost(accountDestination.getDestination(), accountDestination.getAmount());
		
		accounts.add(indexAccountOrigin, accountOrigin);
		accounts.add(indexAccountDestination, accountDestination);
		
		return TransferResponse.toDTO(response1, response2);
	}

}
