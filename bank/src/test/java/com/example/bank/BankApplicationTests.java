package com.example.bank;

import com.example.bank.entity.BankAccount;
import com.example.bank.repository.BankAccountRepository;
import com.example.bank.service.BankService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BankApplicationTests {
	@InjectMocks
	BankService bankService;
	@Mock
	BankAccountRepository bankAccountRepository;

	@Test
	void testCreateAccount() {
		BankAccount bankAccount = new BankAccount();
		bankAccount.setAccountNumber("1234567890");
		bankAccount.setAccountType("Checking");
		bankAccount.setBalance(1000.0);
		when(bankAccountRepository.save(any(BankAccount.class))).thenReturn(bankAccount);
		BankAccount bankResponse = bankService.createBankAccount(bankAccount.getAccountNumber(),bankAccount.getAccountType(),bankAccount.getBalance());
	}

	@Test
	void getBankAccountByAccountNumberTest() {
		BankAccount bankAccount = new BankAccount();
		bankAccount.setAccountNumber("1234567890");
		bankAccount.setAccountType("Checking");
		bankAccount.setBalance(1000.0);
		when(bankAccountRepository.findByAccountNumber(anyString())).thenReturn(bankAccount);
		BankAccount bankResponse = bankService.getBankAccountByAccountNumber(anyString());
	}

	@Test
	void getAllBankAccountsTest() {
		BankAccount bankAccount = new BankAccount();
		bankAccount.setAccountNumber("1234567890");
		bankAccount.setAccountType("Checking");
		bankAccount.setBalance(1000.0);
		when(bankAccountRepository.findAll()).thenReturn(List.of(bankAccount));
		List<BankAccount> accounts =bankService.getAllBankAccounts();
	}
//	@Test
//	void getBankAccountByAccountNumberNullTest() {
//		BankAccount bankAccount = new BankAccount();
//		bankAccount.setAccountNumber("1234567890");
//		bankAccount.setAccountType("Checking");
//		bankAccount.setBalance(1000.0);
//		when(bankAccountRepository.findByAccountNumber(anyString())).thenReturn(null);
//		when(bankService.getBankAccountByAccountNumber(anyString())).thenThrow(RuntimeException.class);
//	}
@Test
void testUpdateAccount() {
	BankAccount bankAccount = new BankAccount();
	bankAccount.setAccountNumber("1234567890");
	bankAccount.setAccountType("Checking");
	bankAccount.setBalance(1000.0);
	when(bankAccountRepository.findByAccountNumber(anyString())).thenReturn(bankAccount);
	when(bankAccountRepository.save(bankAccount)).thenReturn(bankAccount);
	BankAccount bankResponse = bankService.updateBankAccount(bankAccount.getAccountNumber(),bankAccount.getAccountType(),bankAccount.getBalance());

}

	@Test
	void findByAccountNumberTest() {
		BankAccount bankAccount = new BankAccount();
		bankAccount.setAccountNumber("1234567890");
		bankAccount.setAccountType("Checking");
		bankAccount.setBalance(1000.0);
		when(bankAccountRepository.findByAccountNumber(anyString())).thenReturn(bankAccount);
		BankAccount bankResponse = bankService.findByAccountNumber(bankAccount.getAccountNumber());

	}

	@Test
	void deleteById() {
		BankAccount bankAccount = new BankAccount();
		bankAccount.setAccountNumber("1234567890");
		bankAccount.setAccountType("Checking");
		bankAccount.setBalance(1000.0);
		when(bankAccountRepository.findByAccountNumber(anyString())).thenReturn(bankAccount);
		bankService.deleteById(bankAccount.getAccountNumber());

	}

	@Test
	void testPositive() {
		BankAccount bankAccount = new BankAccount();
		bankAccount.setAccountNumber("1234567890");
		bankAccount.setAccountType("Checking");
		bankAccount.setBalance(1000.0);
		try {
			bankService.getBankAccountByAccountNumber(bankAccount.getAccountNumber());
		} catch (Exception e) {
			assertEquals(RuntimeException.class, e.getClass());
			if (bankAccount.getBalance() <= 0) {
				throw new RuntimeException("Balance must be a positive number");
			}
		}
	}

}
