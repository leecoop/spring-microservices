package com.microservices.tutorial.userservice.service;

import com.microservices.tutorial.userservice.model.InternalUser;
import com.microservices.tutorial.userservice.model.User;

/**
 * Created by LEONMAT on 17/2/2020.
 */
public interface UserService {

    User create(User user);

    InternalUser getUserByEmail(String email);

    User get(String userId);
}
