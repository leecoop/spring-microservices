package com.microservices.tutorial.userservice.service.impl;

import com.microservices.tutorial.accountmanagementservice.model.AccountDetails;
import com.microservices.tutorial.userservice.client.AccountManagementClient;
import com.microservices.tutorial.userservice.model.InternalUser;
import com.microservices.tutorial.userservice.model.User;
import com.microservices.tutorial.userservice.model.entity.UserEntity;
import com.microservices.tutorial.userservice.persist.UsersPersist;
import com.microservices.tutorial.userservice.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

/**
 * Created by LEONMAT on 17/2/2020.
 */
@Service
public class UserServiceImpl implements UserService {

    private final UsersPersist persist;
    private final RestTemplate restTemplate;
    private final AccountManagementClient accountManagementClient;


    public UserServiceImpl(UsersPersist persist, RestTemplate restTemplate, AccountManagementClient accountManagementClient) {
        this.persist = persist;
        this.restTemplate = restTemplate;
        this.accountManagementClient = accountManagementClient;
    }


    @Override
    public User create(User user) {
        UserEntity userEntity = (UserEntity) user;
        return persist.save(userEntity);
    }

    @Override
    public InternalUser getUserByEmail(String email) {
        final UserEntity userEntity = persist.findByEmail(email);

        Assert.notNull(userEntity, "User can't be found. Email: " + email);

        return new InternalUser(userEntity.getUserId(), userEntity.getEncryptedPassword(), userEntity.getEmail());
    }

    @Override
    public User get(String userId) {
        final UserEntity user = persist.findByUserId(userId);
        Assert.notNull(user, " User Not found, with id: " + userId);

//       ### RestTemplate ####
//        final ResponseEntity<AccountDetails> accountDetailsResponse = restTemplate.exchange(String.format("http://account-management-service/account/%s", userId), HttpMethod.GET, null, AccountDetails.class);
//        final AccountDetails userAccount =accountDetailsResponse.getBody();
//       ### RestTemplate ####

        final AccountDetails userAccount = accountManagementClient.getUserAccount(userId);
        user.setAccountDetails(userAccount);

        return user;
    }
}
