package com.mdc.model;

import java.util.List;

public class UserModel {
    private String username;
    private String password;
    private String email;
    private String[] roles;



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String... roles) {
        this.roles = roles;
    }

}
