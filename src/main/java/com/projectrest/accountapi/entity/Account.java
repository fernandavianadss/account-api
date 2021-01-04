package com.projectrest.accountapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {
	
	private String type;
	private String destination;
	private long amount;
	
	public void withdraw(long value) {
		amount -= value;
	}
	
	public void deposit(long value) {
		amount += value;
	}
	
	public void transfer(Account accountDestination, long value) {
		withdraw(value);
		accountDestination.deposit(value);
	}
}
