package com.microservices.tutorial.accountmanagementservice.service;

import com.microservices.tutorial.accountmanagementservice.model.AccountDetails;

/**
 * Created by leonmat on 20/2/2020.
 */
public interface AccountService {
    AccountDetails getByUserId(String userId);
}
