package com.microservices.tutorial.userservice.controller;

import com.microservices.tutorial.userservice.model.InternalUser;
import com.microservices.tutorial.userservice.model.User;
import com.microservices.tutorial.userservice.model.entity.UserEntity;
import com.microservices.tutorial.userservice.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

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


    @GetMapping("/{userId}")
    @ResponseStatus(OK)
    public User getUser(@PathVariable String userId) {
        return userService.get(userId);
    }

    @GetMapping("/by-email/{email}")
    InternalUser getUserByEmail(@PathVariable("email") String email) {
        return userService.getUserByEmail(email);
    }
}
