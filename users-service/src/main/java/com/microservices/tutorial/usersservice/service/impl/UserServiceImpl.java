package com.microservices.tutorial.usersservice.service.impl;

import com.microservices.tutorial.usersservice.model.User;
import com.microservices.tutorial.usersservice.model.entity.UserEntity;
import com.microservices.tutorial.usersservice.persist.UsersPersist;
import com.microservices.tutorial.usersservice.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;

/**
 * Created by LEONMAT on 17/2/2020.
 */
@Service
public class UserServiceImpl implements UserService {

    private final UsersPersist persist;

    public UserServiceImpl(UsersPersist persist) {
        this.persist = persist;
    }


    @Override
    public User create(User user) {
        UserEntity userEntity = (UserEntity) user;
        return persist.save(userEntity);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final UserEntity userEntity = persist.findByEmail(email);
        Assert.notNull(userEntity, "User can't be found. Email: " + email);
        return new org.springframework.security.core.userdetails.User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());

    }
}
