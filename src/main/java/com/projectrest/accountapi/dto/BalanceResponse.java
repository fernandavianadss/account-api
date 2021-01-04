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
public class BalanceResponse {
	private long balance;
	
	public BalanceResponse(Account account) {
		balance = account.getAmount();
	}
}
