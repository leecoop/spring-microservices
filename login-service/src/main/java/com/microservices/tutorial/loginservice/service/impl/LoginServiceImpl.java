package com.microservices.tutorial.loginservice.service.impl;

import com.microservices.tutorial.loginservice.client.UserServiceClient;
import com.microservices.tutorial.loginservice.service.LoginService;
import com.microservices.tutorial.userservice.model.InternalUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;

@Service
public class LoginServiceImpl implements LoginService {
    private final UserServiceClient userServiceClient;

    public LoginServiceImpl(UserServiceClient userServiceClient) {
        this.userServiceClient = userServiceClient;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        InternalUser user = getUserByEmail(email);
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());

    }

    @Override
    public InternalUser getUserByEmail(String email) {
        final InternalUser user = userServiceClient.getUserByEmail(email);
        Assert.notNull(user, "User can't be found. Email: " + email);
        return user;
    }
}
