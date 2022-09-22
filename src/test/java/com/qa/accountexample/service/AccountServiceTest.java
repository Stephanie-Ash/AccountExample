package com.qa.accountexample.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.accountexample.entity.Account;
import com.qa.accountexample.repository.AccountRepository;

@SpringBootTest
public class AccountServiceTest {
	
	@Autowired
	private AccountService service;
	
	@MockBean
	private AccountRepository repo;
	
	@Test
	public void testAddAccount() {
		final Account TEST_ACCOUNT = new Account(1, "test-num", "test-name");
		
		Mockito.when(this.repo.save(TEST_ACCOUNT)).thenReturn(TEST_ACCOUNT);
		Assertions.assertThat(this.service.addAccount(TEST_ACCOUNT)).isEqualTo(TEST_ACCOUNT);
		Mockito.verify(this.repo, Mockito.times(1)).save(TEST_ACCOUNT);
		
	}

}
