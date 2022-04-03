package com.example.a2.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="administrator")
public class Administrator {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(unique = true)
    private String name;

    @Column
    private String password;

    @ElementCollection
    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private List<Restaurant> restaurantList = new ArrayList<Restaurant>();

    public Administrator(){};

    public Administrator(String name, String password, List<Restaurant> restaurantList) {
        this.name = name;
        this.password = password;
        this.restaurantList = restaurantList;
    }

    public void addRestaurant(Restaurant restaurant){
        restaurantList.add(restaurant);
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

    public List<Restaurant> getRestaurantList() {
        return restaurantList;
    }

    public void setRestaurantList(List<Restaurant> restaurantList) {
        this.restaurantList = restaurantList;
    }
}
