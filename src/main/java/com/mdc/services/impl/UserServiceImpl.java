package com.mdc.services.impl;

import com.mdc.model.UserModel;
import com.mdc.services.UserService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    Map<String,UserModel> users ;

    public UserServiceImpl() {
        users = new HashMap<>();
        users.put("admin", new UserModel.Builder().setUsername("admin").setPassword("admin").setRoles("ADMIN","USER").build());
        users.put("user", new UserModel.Builder().setUsername("user").setPassword("user").setRoles("USER").build());
    }

    @Override
    public UserModel findByUserName(String user) {
        return users.get(user);
    }
}
