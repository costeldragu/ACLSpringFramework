package com.mdc.model;

public class UserModel {

    private String username;
    private String password;
    private String[] roles;

    public UserModel() {
    }

    UserModel(Builder builder) {
        this.username = builder.username;
        this.password = builder.password;
        this.roles = builder.roles;
    }

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

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String... roles) {
        this.roles = roles;
    }

   public static class Builder {
        private String username;
        private String password;
        private String[] roles;

        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setRoles(String... roles) {
            this.roles = roles;
            return this;
        }

        public UserModel build() {
            return new UserModel(this);
        }
    }
}
