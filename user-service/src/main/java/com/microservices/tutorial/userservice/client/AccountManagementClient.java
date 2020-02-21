package com.microservices.tutorial.userservice.client;

import com.microservices.tutorial.accountmanagementservice.model.AccountDetails;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;

/**
 * Created by leonmat on 20/2/2020.
 */
@FeignClient(name = "account-management-service", fallbackFactory = AccountManagementFallbackFactory.class)
public interface AccountManagementClient {

    @GetMapping("/account/{userId}")
    AccountDetails getUserAccount(@PathVariable("userId") String userId);
}



@Component
class AccountManagementFallbackFactory implements FallbackFactory<AccountManagementClient>{

    @Override
    public AccountManagementClient create(Throwable throwable) {
        return new AccountManagementFallback(throwable);
    }
}

class AccountManagementFallback implements AccountManagementClient{
    Logger logger = LoggerFactory.getLogger(this.getClass());
    private Throwable throwable;

    public AccountManagementFallback(Throwable throwable) {
        this.throwable = throwable;
    }

    @Override
    public AccountDetails getUserAccount(String userId) {
        logger.error(throwable.getLocalizedMessage());
        return null;
    }
}