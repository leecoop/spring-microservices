package com.microservices.tutorial.userservice.client;

import com.microservices.tutorial.accountmanagementservice.model.AccountDetails;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by leonmat on 20/2/2020.
 */
@FeignClient(name = "account-management-service")
public interface AccountManagementClient {

    @GetMapping("/account/{userId}")
    AccountDetails getUserAccount(@PathVariable("userId") String userId);
}
