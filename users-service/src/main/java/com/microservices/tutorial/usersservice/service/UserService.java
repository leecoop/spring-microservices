package com.microservices.tutorial.usersservice.service;

import com.microservices.tutorial.usersservice.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by LEONMAT on 17/2/2020.
 */
public interface UserService extends UserDetailsService{

    User create(User user);
}
