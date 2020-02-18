package com.microservices.tutorial.userservice.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.SocketUtils;

//@Configuration
public class XWebServerFactoryCustomizerConfiguration implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {

    @Value("${port.number.min:8080}")
    private Integer minPort;

    @Value("${port.number.max:8090}")
    private Integer maxPort;

    @Override
    public void customize(ConfigurableServletWebServerFactory factory) {
        int port = SocketUtils.findAvailableTcpPort(minPort, maxPort);
        factory.setPort(port);
        System.getProperties().put("server.port", port);
    }
}
