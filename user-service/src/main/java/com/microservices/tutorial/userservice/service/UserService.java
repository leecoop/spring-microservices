package com.microservices.tutorial.userservice.service;

import com.microservices.tutorial.userservice.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by LEONMAT on 17/2/2020.
 */
public interface UserService extends UserDetailsService{

    User create(User user);

    User getUserByEmail(String email);

    User get(String userId);
}
