package com.sk02.sk02_user_service.domain;

import com.sk02.sk02_user_service.domain.enums.Role;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table (name = "users", indexes = {
        @Index(columnList = "username", unique = true),
        @Index(columnList = "email", unique = true),
        @Index(columnList = "phone", unique = true)})
public class User {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated (EnumType.STRING)
    private Role role;

    private boolean enabled;

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private String phone;
    private Date birthday;

    @OneToOne (cascade = CascadeType.ALL)
    private ClientAttributes clientAttributes;

    @OneToOne (cascade = CascadeType.ALL)
    private ManagerAttributes managerAttributes;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public ClientAttributes getClientAttributes() {
        return clientAttributes;
    }

    public void setClientAttributes(ClientAttributes clientAttributes) {
        this.clientAttributes = clientAttributes;
    }

    public ManagerAttributes getManagerAttributes() {
        return managerAttributes;
    }

    public void setManagerAttributes(ManagerAttributes managerAttributes) {
        this.managerAttributes = managerAttributes;
    }
}
