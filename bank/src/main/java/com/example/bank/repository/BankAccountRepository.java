package com.example.bank.repository;


import com.example.bank.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, String> {
    BankAccount findByAccountNumber(String accountNumber);
    @Transactional
    @Modifying
    void deleteByAccountNumber(String accountNumber);
}


