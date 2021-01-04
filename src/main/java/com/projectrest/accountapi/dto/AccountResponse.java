package com.projectrest.accountapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponse {
	
	private AccountPost destination;
	
	
	public static AccountResponse toDTO(AccountPost accountDestination) {
		return new AccountResponse(accountDestination);
	}

}
