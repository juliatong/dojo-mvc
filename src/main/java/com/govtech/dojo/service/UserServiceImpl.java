package com.govtech.dojo.service;

import com.govtech.dojo.model.Role;
import com.govtech.dojo.model.User;
import com.govtech.dojo.repository.RoleRespository;
import com.govtech.dojo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashSet;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Autowired
    private RoleRespository roleRespository;

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void saveUser(User user) {
        user.setPassword(user.getPassword());
        user.setActive(1);
        Role userRole = roleRespository.findByRole("ADMIN");
        if (userRole == null) {
            userRole = new Role();
        }
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(user);
    }
}