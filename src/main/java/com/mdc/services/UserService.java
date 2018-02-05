package com.mdc.services;

import com.mdc.model.UserModel;

public interface UserService {

    UserModel findByUserName(String user);
}
