package com.sk02.sk02_gui_service.model;

public class User {

    private Long id;
    private String email;
    private String role;
    private String username;

    public User(Long id, String email, String role, String username) {
        this.id = id;
        this.email = email;
        this.role = role;
        this.username = username;
    }

    public User(){

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
