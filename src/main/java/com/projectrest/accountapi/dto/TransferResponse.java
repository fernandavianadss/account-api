package com.projectrest.accountapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransferResponse {
	
	private AccountPost origin;
	private AccountPost destination;
	
	/*
	public TransferResponse(AccountResponse accountOrigin, AccountResponse accountDestination) {
		origin = accountOrigin;
		destination = accountDestination;
	}
*/
	public static TransferResponse toDTO(AccountPost accountOrigin, AccountPost accountDestination) {
		return new TransferResponse(accountOrigin, accountDestination);
	}

}
