package com.sk02.sk02_gui_service.model;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Base64;

public class UserData {

    private static UserData instance;

    private String token;
    private String role;
    private Long id;
    private String email;
    private String username;

    private UserData(){
    }

    public static UserData getInstance(){
        if(instance == null){
            instance = new UserData();
        }
        return instance;
    }

    public void logOut(){
        token = null;
        role = null;
        id = null;
        email = null;
        username = null;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
