package com.microservices.tutorial.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InternalUser {
    private String userId;
    private String password;
    private String email;
}
