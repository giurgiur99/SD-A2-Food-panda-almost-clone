package com.example.a2.DTO;


import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class CustomerDTO {
    private String name;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
