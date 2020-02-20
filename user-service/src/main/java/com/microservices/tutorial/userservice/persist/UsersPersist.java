package com.microservices.tutorial.userservice.persist;

import com.microservices.tutorial.userservice.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by LEONMAT on 17/2/2020.
 */
public interface UsersPersist extends JpaRepository<UserEntity, Long> {

    UserEntity findByEmail(String email);

    UserEntity findByUserId (String userId);
}
