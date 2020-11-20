package com.govtech.dojo.service;

import com.govtech.dojo.model.User;

public interface UserService {
	  
	 public User findUserByEmail(String email);
	 
	 public void saveUser(User user);
	}