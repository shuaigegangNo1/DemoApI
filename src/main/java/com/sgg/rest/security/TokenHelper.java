package com.sgg.rest.security;


import static com.sgg.rest.security.SecurityConstants.*;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenHelper {


    @Autowired
    UserDetailsService userDetailsService;

    private SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;


    public String generateToken(String username) {
        return Jwts.builder()
//                .setIssuer( APP_NAME )
                .setSubject(username)
                .setIssuedAt(generateCurrentDate())
                .setExpiration(generateExpirationDate())
                .signWith( SIGNATURE_ALGORITHM, SECRET )
                .compact();
    }



    private long getCurrentTimeMillis() {
    	return new DateTime().getMillis();
    }

    private Date generateCurrentDate() {
        return new Date(getCurrentTimeMillis());
    }

    private Date generateExpirationDate() {
        return new Date(getCurrentTimeMillis() + EXPIRATION_TIME);
    }

    public String getToken( HttpServletRequest request ) {
        /**
         *  Getting the token from Authentication header
         *  e.g Bearer your_token
         */
        String authHeader = request.getHeader(HEADER_STRING);
        if ( authHeader != null && authHeader.startsWith(TOKEN_PREFIX)) {
        	return authHeader.replace(TOKEN_PREFIX, "");
        }

        return null;
    }

    public String getUsernameFromToken(String token) {
    	String user = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
    	return user;
    }


}
