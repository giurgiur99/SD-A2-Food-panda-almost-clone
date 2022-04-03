package com.example.a2.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "restaurant")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String deliveryZones;

    @Column(nullable = false)
    private String description;

    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", unique = true)
    private List<Menu> menuList = new ArrayList<>();

    public Restaurant(){};
    public Restaurant(String name, String location, String deliveryZones) {
        this.name = name;
        this.location = location;
        this.deliveryZones = deliveryZones;
    }

    public void addMenu(Menu menu){
        menuList.add(menu);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDeliveryZones() {
        return deliveryZones;
    }

    public void setDeliveryZones(String deliveryZones) {
        this.deliveryZones = deliveryZones;
    }

    public List<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
