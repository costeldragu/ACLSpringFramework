package com.mdc.services;

import com.mdc.model.UserModel;

import java.util.List;

public interface UserService {

    UserModel findUserByEmail(String email);

    UserModel findByUsername(String username);



}
