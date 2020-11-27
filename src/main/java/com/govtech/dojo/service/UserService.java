package com.govtech.dojo.service;

import com.govtech.dojo.model.User;

public interface UserService {

    User findUserByEmail(String email);

    void saveUser(User user);
}