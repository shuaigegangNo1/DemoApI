package com.sgg.rest.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import static com.sgg.rest.security.SecurityConstants.*;

@Component
public class AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	@Autowired
	TokenHelper tokenHelper;
	
	@Autowired
	ObjectMapper objectMapper;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication ) throws IOException, ServletException {
		clearAuthenticationAttributes(request);
		String username = (String)authentication.getPrincipal();

		String jws = tokenHelper.generateToken( username); 
		UserTokenState userTokenState = new UserTokenState(jws, EXPIRATION_TIME);
		String jwtResponse = objectMapper.writeValueAsString( userTokenState );
		// JWT is also in the response
		response.setContentType("application/json");
		response.getWriter().write( jwtResponse );
	}
}
