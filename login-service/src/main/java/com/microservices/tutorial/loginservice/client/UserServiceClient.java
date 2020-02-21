package com.microservices.tutorial.loginservice.client;

import com.microservices.tutorial.userservice.model.InternalUser;
import com.microservices.tutorial.userservice.model.User;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by leonmat on 20/2/2020.
 */
@FeignClient(name = "user-service", fallbackFactory = UserServiceFallbackFactory.class)
public interface UserServiceClient {

    @GetMapping("/users/by-email/{email}")
    InternalUser getUserByEmail(@PathVariable("email") String email);
}


@Component
class UserServiceFallbackFactory implements FallbackFactory<UserServiceClient> {

    @Override
    public UserServiceClient create(Throwable throwable) {
        return new UserServiceFallback(throwable);
    }
}

class UserServiceFallback implements UserServiceClient {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    private Throwable throwable;

    public UserServiceFallback(Throwable throwable) {
        this.throwable = throwable;
    }


    @Override
    public InternalUser getUserByEmail(String email) {
        logger.error(throwable.getLocalizedMessage());
        return null;
    }
}