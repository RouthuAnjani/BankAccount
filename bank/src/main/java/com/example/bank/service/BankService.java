package com.example.bank.service;
import com.example.bank.entity.BankAccount;
import com.example.bank.repository.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class BankService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    public BankAccount createBankAccount(String accountNumber, String accountType, Double balance) {
//        if (bankAccountRepository.findByAccountNumber(accountNumber).isPresent()) {
//            throw new RuntimeException("Account number already exists");
//        }
//
//        if (balance <= 0) {
//            throw new RuntimeException("Initial balance must be a positive number");
//        }

        BankAccount bankAccount = new BankAccount();
        bankAccount.setAccountNumber(accountNumber);
        bankAccount.setAccountType(accountType);
        bankAccount.setBalance(balance);
        validateBankAccount(bankAccount);

        return bankAccountRepository.save(bankAccount);
    }

    public BankAccount getBankAccountByAccountNumber(String accountNumber) {
        Optional<BankAccount> optionalBankAccount = Optional.ofNullable(bankAccountRepository.findByAccountNumber(accountNumber));

        if (!optionalBankAccount.isPresent()) {
            throw new RuntimeException("Account not found");
        }
        return optionalBankAccount.get();
    }

    public List<BankAccount> getAllBankAccounts() {
        return bankAccountRepository.findAll();
    }

    public BankAccount updateBankAccount(String accountNumber, String accountType, Double balance) {
        BankAccount bankAccount = getBankAccountByAccountNumber(accountNumber);

        if (balance <= 0) {
            throw new RuntimeException("Balance must be a positive number");
        }

        bankAccount.setAccountType(accountType);
        bankAccount.setBalance(balance);

        return bankAccountRepository.save(bankAccount);
    }

    public BankAccount findByAccountNumber(String accountNumber) {
        return bankAccountRepository.findByAccountNumber(accountNumber);
    }

    public String deleteById(String accountNumber) {
        if (bankAccountRepository.findByAccountNumber(accountNumber) == null) {
            throw new IllegalArgumentException("Account number not exists");
        }
        bankAccountRepository.deleteByAccountNumber(accountNumber);
        return "DELETED";
    }
    private void validateBankAccount(BankAccount bankAccount) {
        if (bankAccountRepository.findByAccountNumber(bankAccount.getAccountNumber()) != null) {
            throw new IllegalArgumentException("Account number already exists");
        }
        if (!Arrays.asList("checking", "savings", "money market").contains(bankAccount.getAccountType().toLowerCase())) {
            throw new IllegalArgumentException("Invalid account type");
        }
        if (bankAccount.getBalance() < 0) {
            throw new IllegalArgumentException("Initial balance must be positive");
        }
    }
}
