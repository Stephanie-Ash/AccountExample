package com.qa.accountexample.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.qa.accountexample.dto.AccountDTO;
import com.qa.accountexample.entity.Account;
import com.qa.accountexample.exception.AccountNotFoundException;
import com.qa.accountexample.repository.AccountRepository;

@Service
public class AccountService {
	
	private AccountRepository repo;
	
	private ModelMapper mapper;
	
	public AccountService(AccountRepository repo, ModelMapper mapper) {
		this.repo = repo;
		this.mapper = mapper;
	}
	
	private AccountDTO mapToDTO(Account account) {
		return this.mapper.map(account, AccountDTO.class);
	}
	
	public Account addAccount(Account account) {
		return this.repo.save(account);

	}
	
	public List<Account> getAllAccounts() {
		return this.repo.findAll();
	}
	
	public Account updateAccount(Long id, Account newAccount) {
		Optional<Account> existingOptional = this.repo.findById(id);
		Account existing = existingOptional.get();
		
		existing.setAccountNumber(newAccount.getAccountNumber());
		existing.setName(newAccount.getName());
		
		return this.repo.save(existing);

	}
	
	public boolean removeAccount(Long id) {
		this.repo.deleteById(id);
		boolean exists = this.repo.existsById(id);
		return !exists;
	}
	
	public Account readById(Long id) {
		return this.repo.findById(id).orElseThrow(AccountNotFoundException::new);
	}
	
	public AccountDTO readByIdToDTO(Long id) {
		Account found = this.repo.findById(id).orElseThrow(AccountNotFoundException::new);
		return this.mapToDTO(found);
	}

}
