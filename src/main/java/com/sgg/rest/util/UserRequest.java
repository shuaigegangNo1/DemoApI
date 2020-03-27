package com.sgg.rest.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.sgg.rest.model.User;
import static com.sgg.rest.security.SecurityConstants.*;
public class UserRequest {
	   public static User getCurrentUser(){
	      HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//	      User user = (User)request.getAttribute(CURRENT_USER_REQ);
			HttpSession session = request.getSession(true);
			User user=(User) session.getAttribute(CURRENT_USER_REQ);
	      return user;
	   }

	}
