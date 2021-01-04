package com.projectrest.accountapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransferPost {
	
	private String type;
	private String origin;
	private long amount;
	private String destination;
	
}
