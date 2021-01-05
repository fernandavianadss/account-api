package com.projectrest.accountapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projectrest.accountapi.dto.AccountResponse;
import com.projectrest.accountapi.dto.BalanceResponse;
import com.projectrest.accountapi.dto.TransferPost;
import com.projectrest.accountapi.dto.TransferResponse;
import com.projectrest.accountapi.dto.WithDrawPost;
import com.projectrest.accountapi.entity.Account;
import com.projectrest.accountapi.service.AccountService;

@RestController
@RequestMapping("/")
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	@GetMapping(path = "/reset")
	public ResponseEntity<String> reset() {
		accountService.reset();
		return new ResponseEntity<String>("Ok", HttpStatus.OK);
	}
	
	@GetMapping(path = "balance/account_id/{id}")
	public ResponseEntity<BalanceResponse> findByAccount(@PathVariable String id){
		Account entity = accountService.findByAccount(id);
		return new ResponseEntity<BalanceResponse>(new BalanceResponse(entity), HttpStatus.OK);
	}
	
	@PostMapping(path = "/create")
	public ResponseEntity<AccountResponse> save(@RequestBody Account accountPostResquestBody){
		return new ResponseEntity<AccountResponse>(accountService.save(accountPostResquestBody), HttpStatus.CREATED);
	}
	
	@PostMapping(path = "/deposit")
	public ResponseEntity<AccountResponse> deposit(@RequestBody Account accountPostResquestBody){
		AccountResponse accountResponse = accountService.deposit(accountPostResquestBody, accountPostResquestBody.getType());
		return new ResponseEntity<AccountResponse>(accountResponse, HttpStatus.CREATED);
	}
	
	@PostMapping(path = "/withdraw")
	public ResponseEntity<AccountResponse> withdraw(@RequestBody WithDrawPost withDrawPostResquestBody){
		AccountResponse accountResponse = accountService.whithdraw(withDrawPostResquestBody, withDrawPostResquestBody.getType());
		return new ResponseEntity<AccountResponse>(accountResponse, HttpStatus.CREATED);
	}
	
	@PostMapping(path = "/transfer")
	public ResponseEntity<TransferResponse> transfer(@RequestBody TransferPost transferPostResquestBody){
		TransferResponse transferResponse = accountService.transfer(transferPostResquestBody);
		return new ResponseEntity<TransferResponse>(transferResponse, HttpStatus.CREATED);
	}
}
