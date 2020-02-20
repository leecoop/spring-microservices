package com.microservices.tutorial.accountmanagementservice.service.impl;

import com.microservices.tutorial.accountmanagementservice.model.AccountDetails;
import com.microservices.tutorial.accountmanagementservice.service.AccountService;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 * Created by leonmat on 20/2/2020.
 */

@Service
public class AccountServiceImpl implements AccountService {

    private final Environment environment;

    public AccountServiceImpl(Environment environment) {
        this.environment = environment;
    }

    @Override
    public AccountDetails getByUserId(String userId) {
        return AccountDetails.builder()
                .name("Manager Account")
                .localPort(environment.getProperty("local.server.port"))
                .build();
    }
}
