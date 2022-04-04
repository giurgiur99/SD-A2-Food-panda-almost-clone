package com.example.a2.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @ElementCollection
    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_food_id")
    private List<Food> food;

    @Column
    private Status status;

    public Customer(){};

    public Customer( String name, String password, Status status) {
        this.name = name;
        this.password = password;
        this.status = status;
    }

    public void encrypt(){
        StringBuilder encryptedPass = new StringBuilder(this.password);
        for(int i = 0 ; i < this.password.length() ; i++){
            encryptedPass.setCharAt(i, (char) (encryptedPass.charAt(i) + 5));
        }
        System.out.println(encryptedPass);
        this.password = String.valueOf(encryptedPass);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Food> getFood() {
        return food;
    }

    public void addFood(Food food) {
        this.food.add(food);
    }
}
