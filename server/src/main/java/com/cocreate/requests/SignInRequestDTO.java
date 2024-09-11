package com.cocreate.requests;

import jakarta.validation.constraints.NotEmpty;



public class SignInRequestDTO {
    @NotEmpty(message = "User name cannot be empty")
    private String userName;

    @NotEmpty(message = "Password cannot be empty")
    private String password;

    public String getUserName() {
        return this.userName;
    }

    public String getPassword() {
        return this.password;
    }
}
