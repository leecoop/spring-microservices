package com.microservices.tutorial.loginservice.service;

import com.microservices.tutorial.userservice.model.InternalUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface LoginService extends UserDetailsService {
    InternalUser getUserByEmail(String email);
}
