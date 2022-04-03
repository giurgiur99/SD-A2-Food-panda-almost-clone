package com.example.a2.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "menu")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String name;

    @Enumerated(EnumType.ORDINAL)
    private Categories categories;

    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id", unique = true)
    private List<Food> foods = new ArrayList<Food>();

    public Menu() {

    }

    public Menu(Categories categories, List<Food> foods){
        this.categories = categories;
        this.foods = foods;

    }

    public void addFood(Food food){
        foods.add(food);
    }

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
