package com.qa.accountexample.controller;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.accountexample.dto.AccountDTO;
import com.qa.accountexample.entity.Account;
import com.qa.accountexample.service.AccountService;

@RestController
public class AccountController {
	
	private AccountService service;
	
	public AccountController(AccountService service) {
		this.service = service;
	}
	
	@PostMapping("/create")
	public Account addAccount(@RequestBody Account account) {
		return this.service.addAccount(account);
	}
	
	@GetMapping("/getAll")
	public List<Account> getAllAccounts() {
		return this.service.getAllAccounts();
	}
	
	@PutMapping("/update")
	public Account updateAccount(@PathParam("id") Long id, @RequestBody Account account) {
		return this.service.updateAccount(id, account);
	}
	
	@GetMapping("read/{id}")
	public Account getAccountById(@PathVariable Long id) {
		return this.service.readById(id);
	}
	
	@GetMapping("read/dto/{id}")
	public AccountDTO getAccountByIdToDTO(@PathVariable Long id) {
		return this.service.readByIdToDTO(id);
	}
	
	@DeleteMapping("/delete/{id}")
	public boolean removeAccount(@PathVariable Long id) {
		return this.service.removeAccount(id);
	}

}
