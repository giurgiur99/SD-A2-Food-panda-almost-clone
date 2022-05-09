package com.example.a2.services;

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

    /**
     * Service used to insert a new restaurant in database
     * @param restaurant
     */
    public void insertRestaurant(Restaurant restaurant){
        restaurantRepo.save(restaurant);
    }

    /**
     * Service used to retrieve restaurant based on restaurantName
     * @param restaurantName
     * @return
     */
    public Restaurant getRestaurant(String restaurantName){ return restaurantRepo.findByName(restaurantName); }

    /**
     * Service used to retrive
     * @param restaurant
     * @param menu
     */
    public void insertMenu(Restaurant restaurant, Menu menu){
        restaurant.addMenu(menu);
        restaurantRepo.save(restaurant);
    }

    /**
     * Save menu of a restaurasnt
     * @param restaurant
     * @param menu
     */
    public void saveMenu(Restaurant restaurant, Menu menu){
        restaurantRepo.save(restaurant);
        menuRepo.save(menu);
    }

    /**
     * Service used to retrieve all restaurants
     * @return
     */
    public List<Restaurant> getRestaurants(){
        return restaurantRepo.findAll();
    }

    public List<Menu> getMenusByRestaurant(String restaurantName){
        return restaurantRepo.findByName(restaurantName).getMenuList();
    }

    /**
     * Service used to retrieve food based on foodName
     * @param foodName
     * @return
     */
    public Food findFood(String foodName){
       return foodRepo.findByName(foodName);
    }


    public Menu getMenuByName(String restaurantName, String menuName) {
        try {
            List<Menu> menuList = restaurantRepo.findByName(restaurantName).getMenuList();
            for (Menu menu : menuList) {
                if (menu.getName().equals(menuName))
                    return menu;
            }
            return null;
        }catch (Exception e){
            return null;
        }
    }
}
