package com.microservices.tutorial.accountmanagementservice.controller;

import com.microservices.tutorial.accountmanagementservice.model.AccountDetails;
import com.microservices.tutorial.accountmanagementservice.service.AccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by leonmat on 20/2/2020.
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/{userId}")
    public AccountDetails getUserAccount(@PathVariable String userId) {
        return accountService.getByUserId(userId);
    }
}
