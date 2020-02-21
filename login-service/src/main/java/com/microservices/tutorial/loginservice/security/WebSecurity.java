package com.microservices.tutorial.loginservice.security;

import com.microservices.tutorial.loginservice.service.LoginService;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by LEONMAT on 17/2/2020.
 */

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    private final LoginService loginService;
    private final Environment environment;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public WebSecurity(LoginService loginService, Environment environment, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.loginService = loginService;
        this.environment = environment;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.authorizeRequests().antMatchers("/**").permitAll()
                .and().addFilter(getAuthenticationFilter());
    }

    private AuthenticationFilter getAuthenticationFilter() throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(loginService, environment);
        authenticationFilter.setAuthenticationManager(authenticationManager());
        return authenticationFilter;

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(loginService).passwordEncoder(bCryptPasswordEncoder);
    }
}
