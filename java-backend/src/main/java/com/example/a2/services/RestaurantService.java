package com.example.a2.services;

import com.example.a2.model.Categories;
import com.example.a2.model.Food;
import com.example.a2.model.Menu;
import com.example.a2.model.Restaurant;
import com.example.a2.repositories.FoodRepo;
import com.example.a2.repositories.MenuRepo;
import com.example.a2.repositories.RestaurantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {
    @Autowired
    private RestaurantRepo restaurantRepo;

    @Autowired
    private MenuRepo menuRepo;

    @Autowired
    private FoodRepo foodRepo;


    public void insertRestaurant(Restaurant restaurant){
        restaurantRepo.save(restaurant);
    }
    public Restaurant getRestaurant(String restaurantName){ return restaurantRepo.findByName(restaurantName); }
    public void insertMenu(Restaurant restaurant, Menu menu){
        restaurant.addMenu(menu);
        restaurantRepo.save(restaurant);
    }
    public List<Restaurant> getRestaurants(){
        return restaurantRepo.findAll();
    }

    public List<Menu> getMenusByRestaurant(String restaurantName){
        return restaurantRepo.findByName(restaurantName).getMenuList();
    }

    public Food findFood(String foodName){
       return foodRepo.findByName(foodName);
    }


}
