package com.example.bank.controller;
import com.example.bank.entity.BankAccount;
import com.example.bank.repository.BankAccountRepository;
import com.example.bank.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/bank_accounts")
public class BankController {

    @Autowired
    private BankService bankService;
    @PostMapping
    public BankAccount createBankAccount(@RequestBody BankAccount bankAccount) {
        return bankService.createBankAccount(bankAccount.getAccountNumber(),bankAccount.getAccountType(),bankAccount.getBalance());
    }

    @GetMapping("/{accountNumber}")
    public BankAccount getBankAccount(@PathVariable String accountNumber) {
        return bankService.findByAccountNumber(accountNumber);
    }

    @GetMapping
    public List<BankAccount> getAllBankAccounts() {
        return bankService.getAllBankAccounts();
    }

    @PutMapping("/{accountNumber}")
    public BankAccount updateBankAccount(@PathVariable String accountNumber, @RequestBody BankAccount bankAccount) {
        return bankService.updateBankAccount(accountNumber,bankAccount.getAccountType(),bankAccount.getBalance());
    }

    @DeleteMapping("/{accountNumber}")
    public String deleteBankAccount(@PathVariable String accountNumber) {
        return bankService.deleteById(accountNumber);
    }


}

