package com.mdc.services.impl;

import com.mdc.model.UserModel;
import com.mdc.services.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    Map<String,UserModel> users ;

    /**
     * The roles must be prefixed with ROLE_ in order to work
     */
    public UserServiceImpl() {
        users = new HashMap<>();
        users.put("admin", new UserModel.Builder().setUsername("admin").setPassword(new BCryptPasswordEncoder().encode("admin")).setRoles("ROLE_ADMIN","ROLE_USER").build());
        users.put("user", new UserModel.Builder().setUsername("user").setPassword(new BCryptPasswordEncoder().encode("user")).setRoles("ROLE_USER").build());
    }

    @Override
    public UserModel findByUserName(String user) {
        return users.get(user);
    }
}
