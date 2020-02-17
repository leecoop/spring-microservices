package com.microservices.tutorial.usersservice.controller;

import com.microservices.tutorial.usersservice.model.User;
import com.microservices.tutorial.usersservice.model.entity.UserEntity;
import com.microservices.tutorial.usersservice.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;

/**
 * Created by LEONMAT on 17/2/2020.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public User createUser(@RequestBody @Valid UserEntity user) {

        return userService.create(user);

    }
}
