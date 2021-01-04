package com.projectrest.accountapi.dto;

import com.projectrest.accountapi.entity.Account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountPost {
	
	private String id;
	private long balance;
	
	public AccountPost(Account account) {
		id = account.getDestination();
		balance = account.getAmount();
	}
	
	public static AccountPost toDTO(Account account) {
		return new AccountPost(account.getDestination(), account.getAmount());
	}


}
