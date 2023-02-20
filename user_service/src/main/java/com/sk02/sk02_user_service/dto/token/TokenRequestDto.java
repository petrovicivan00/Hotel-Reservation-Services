package com.sk02.sk02_user_service.dto.token;

import javax.validation.constraints.Email;

public class TokenRequestDto {

    @Email
    private String email;
    private String password;

    public TokenRequestDto() {}

    public TokenRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}