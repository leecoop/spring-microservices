package com.microservices.tutorial.accountmanagementservice.model;

import lombok.Builder;
import lombok.Data;

/**
 * Created by leonmat on 20/2/2020.
 */
@Data
@Builder
public class AccountDetails {

    private String name;
    private String localPort;

}
