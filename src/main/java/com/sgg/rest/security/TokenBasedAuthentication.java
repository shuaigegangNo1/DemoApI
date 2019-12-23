package com.sgg.rest.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;


public class TokenBasedAuthentication extends AbstractAuthenticationToken {

    private String token;
    private final String principle;

    public TokenBasedAuthentication( String principle ) {
        super( AuthorityUtils.NO_AUTHORITIES );
        this.principle = principle;
    }

    public String getToken() {
        return token;
    }

    public void setToken( String token ) {
        this.token = token;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public String getPrincipal() {
        return principle;
    }

}
