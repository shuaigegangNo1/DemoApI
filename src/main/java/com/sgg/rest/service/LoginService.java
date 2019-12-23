package com.sgg.rest.service;

//import org.slf4j.Logger;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sgg.rest.model.User;
@Service
public class LoginService {
	@Autowired
	private UserService userService;
	@Autowired
	private BCryptPasswordEncoder encode;
	private static final Logger LOGGER = Logger.getLogger(LoginService.class);

	public User Login(String name, String password) {
		LOGGER.info(">>>> start enter Login>>>>");
		User user = userService.findUserByName(name);
		if (user!=null) {
			Boolean match = encode.matches(password, user.getPassword());
			if (match) {
				return user;
			}
		}
		return null;
	}
	public User loadUserByUserId(String userId) {
		return userService.findUserByName(userId);
	}
}
