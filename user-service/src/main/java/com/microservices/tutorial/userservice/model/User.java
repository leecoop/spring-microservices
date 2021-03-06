package com.microservices.tutorial.userservice.model;

import com.microservices.tutorial.accountmanagementservice.model.AccountDetails;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.validation.constraints.Email;

/**
 * Created by LEONMAT on 17/2/2020.
 */
@MappedSuperclass
@Data
public class User {

    @Column(nullable = false, length = 50)
    private String firstName;

    @Column(nullable = false, length = 50)
    private String lastName;

    @Transient
    private String password;

    @Email
    @Column(nullable = false, unique = true)
    private String email;

    private String userId;

    @Transient
    private AccountDetails accountDetails;

}
