package com.microservices.tutorial.netflixzuulapigatewayserver.security.filter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by leonmat on 18/2/2020.
 */
public class AuthorizationFilter extends BasicAuthenticationFilter {

    private final String AUTHORIZATION_TOKEN_HEADER_PREFIX;
    private final String TOKEN_SECRET;

    public AuthorizationFilter(AuthenticationManager authenticationManager, Environment environment) {
        super(authenticationManager);

        AUTHORIZATION_TOKEN_HEADER_PREFIX = environment.getProperty("authorization.token.header.prefix");
        TOKEN_SECRET = environment.getProperty("token.secret");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (Objects.isNull(authorizationHeader) || !authorizationHeader.startsWith(AUTHORIZATION_TOKEN_HEADER_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        try {
            UsernamePasswordAuthenticationToken authentication = getAuthentication(request, authorizationHeader);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (MalformedJwtException e) {
            SecurityContextHolder.clearContext();
            this.onUnsuccessfulAuthentication( response, e);
            return;
        }

        chain.doFilter(request, response);

    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request, String authorizationHeader) {

        String token = authorizationHeader.replace(AUTHORIZATION_TOKEN_HEADER_PREFIX, "");

        String userId = Jwts.parser()
                .setSigningKey(TOKEN_SECRET)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();

        if (Objects.isNull(userId)) {
            return null;
        }

        return new UsernamePasswordAuthenticationToken(userId, null, new ArrayList<>());
    }

    protected void onUnsuccessfulAuthentication(HttpServletResponse response, RuntimeException failed) throws IOException {

        String message = "Error occurred during authentication process.";

        if(failed instanceof MalformedJwtException){
            message = "Security token is invalid.";
        }


        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.TEXT_PLAIN_VALUE);
        response.getOutputStream().write(message.getBytes());
    }
}
