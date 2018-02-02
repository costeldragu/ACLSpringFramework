package com.mdc.services.impl;

import com.mdc.model.UserModel;
import com.mdc.services.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private Map<String, UserModel> users;

    public UserServiceImpl() {
        users = new HashMap<>();

        UserModel user1 = new UserModel();
        user1.setEmail("user@user.ro");
        user1.setUsername("user");
        user1.setPassword(new BCryptPasswordEncoder().encode("user"));
        user1.setRoles("USER");
        users.put("user@user.ro", user1);
        users.put("user", user1);

        UserModel user2 = new UserModel();
        user1.setEmail("admin@user.ro");
        user1.setUsername("admin");
        user1.setPassword(new BCryptPasswordEncoder().encode("admin"));
        user1.setRoles("ADMIN", "USER");
        users.put("admin@user.ro", user2);
        users.put("admin", user2);
    }

    @Override
    public UserModel findUserByEmail(String email) {
        return users.get(email);
    }

    @Override
    public UserModel findByUsername(String username) {
        return users.get(username);
    }


}
