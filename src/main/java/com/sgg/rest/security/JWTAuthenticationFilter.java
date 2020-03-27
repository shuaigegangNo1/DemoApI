package com.sgg.rest.security;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sgg.rest.model.User;
import com.sgg.rest.service.LoginService;


import static com.sgg.rest.security.SecurityConstants.*;

public class JWTAuthenticationFilter extends AbstractAuthenticationProcessingFilter{

	private LoginService loginService;

    public JWTAuthenticationFilter(LoginService loginService, AuthenticationSuccessHandler successHandler) {
    	super(new AntPathRequestMatcher("/login", "POST"));
    	this.setAuthenticationSuccessHandler(successHandler);
        this.loginService = loginService;
    }

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, 
			HttpServletResponse response)
			throws AuthenticationException, IOException {
		User creds = new ObjectMapper()
                .readValue(request.getInputStream(), User.class);
		User userEntity = loginService.Login(creds.getName(), creds.getPassword());
		if (userEntity != null){
			request.setAttribute(CURRENT_USER_REQ, userEntity);
			User u=(User) request.getAttribute(CURRENT_USER_REQ);
			HttpSession session = request.getSession(true);
			session.setAttribute(CURRENT_USER_REQ, userEntity);

			System.out.println(">>>>>user name"+u.getName());
			return new TokenBasedAuthentication(creds.getName());
		}
		throw new AuthenticationException("用户验证失败:" + creds.getName()){{}};
	}

}

