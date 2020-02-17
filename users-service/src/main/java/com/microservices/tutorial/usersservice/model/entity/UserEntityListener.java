package com.microservices.tutorial.usersservice.model.entity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.UUID;

/**
 * Created by LEONMAT on 17/2/2020.
 */
public class UserEntityListener {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserEntityListener(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PrePersist
    private void setUserId(UserEntity user) {

        user.setUserId(UUID.randomUUID().toString());

        if (!StringUtils.isEmpty(user.getPassword())) {
            user.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }

    }

    @PreUpdate
    private void setPassword(UserEntity user) {
        if (!StringUtils.isEmpty(user.getPassword())) {
            user.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }
    }
}
