package com.sgg.rest.security;

import static com.sgg.rest.security.SecurityConstants.CURRENT_USER_REQ;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.sgg.rest.model.User;
import com.sgg.rest.service.LoginService;


public class JWTAuthorizationFilter extends OncePerRequestFilter {

	private TokenHelper tokenHelper;
	
	private LoginService loginService;

    public JWTAuthorizationFilter(TokenHelper tokenHelper, LoginService loginService) {
    	this.tokenHelper = tokenHelper;
    	this.loginService = loginService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
    	
    	String token = tokenHelper.getToken(req);
    	if (token != null){
    		//TODO: check expiration
    		String userId = tokenHelper.getUsernameFromToken(token);
    		if (userId != null){
    			User userEntity = loginService.loadUserByUserId(userId);
    			if (userEntity!=null){
    				req.setAttribute(CURRENT_USER_REQ, userEntity);
    				SecurityContextHolder.getContext().setAuthentication(new TokenBasedAuthentication(userId));
    			}
    		}
    	}
        chain.doFilter(req, res);
    }

}

