package com.microservices.tutorial.userservice.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.microservices.tutorial.userservice.model.User;
import lombok.Data;

import javax.persistence.*;


/**
 * Created by LEONMAT on 17/2/2020.
 */
@Data
@Entity
@Table(name = "users")
@EntityListeners({UserEntityListener.class})
@JsonIgnoreProperties(value = {"id", "encryptedPassword"})
public class UserEntity extends User {

    @Id
    @GeneratedValue
    private long id;

    private String encryptedPassword;

}
