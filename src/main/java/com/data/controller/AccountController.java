package com.data.controller;

import com.data.entity.Account;
import com.data.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountController {
    @Autowired
    private AccountRepository accountRepo;

    @GetMapping("/account")
    public ResponseEntity<?> getProducts() {
        List<Account> accounts = accountRepo.findAll();

        return ResponseEntity.ok(accounts);
    }
}
