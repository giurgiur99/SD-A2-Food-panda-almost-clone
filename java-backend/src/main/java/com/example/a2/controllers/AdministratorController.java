package com.example.a2.controllers;

import com.example.a2.DTO.*;
import com.example.a2.model.*;
import com.example.a2.services.AdministratorService;
import com.example.a2.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AdministratorController {
    @Autowired
    private AdministratorService administratorService;
    @Autowired
    private RestaurantService restaurantService;

    @PostMapping("/administrator/add")
    public ResponseEntity addAdministrator(@RequestBody AdministratorDTO administratorDTO){
        List<Restaurant> restaurantList = new ArrayList<>();
        Administrator administrator = new Administrator(administratorDTO.getName(), administratorDTO.getPassword(),restaurantList);
        try {
            administratorService.insertAdministrator(administrator);
        }catch (Exception e){
            System.out.println("-- This user already exists! --");
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("-- This user already exists! --");
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(administrator);
    }

    @PostMapping("/add")
    public ResponseEntity addRestaurantToAdministrator(@RequestParam String name, @RequestBody RestaurantDTO restaurantDTO) {
        Administrator administrator = administratorService.getAdministrator(name);
        Restaurant restaurant = new Restaurant(restaurantDTO.getName(), restaurantDTO.getLocation(), restaurantDTO.getDeliveryZones());

//        try {
            administratorService.addRestaurantToAdministrator(restaurant, administrator);
//        }catch (Exception e){
//            System.out.println("-- This admin already has this restaurant registered! --");
//            return ResponseEntity.status(HttpStatus.CONFLICT).body("-- This admin already has this restaurant registered! --");
//        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(restaurant);
    }

    @PostMapping("/administrator/menu")
    public ResponseEntity createMenu(@RequestParam String restaurantName, @RequestParam String categories, @RequestParam String menuName, @RequestBody FoodDTO foodDTO){
        Restaurant restaurant = restaurantService.getRestaurant(restaurantName);

        Food food = new Food(foodDTO.getName(), foodDTO.getDescription(), foodDTO.getPrice());
        Categories categories1 = Categories.valueOf(categories);
        Menu menu = new Menu();
        menu.setCategories(categories1);
        menu.addFood(food);
        menu.setName(menuName);
        try {
            restaurantService.insertMenu(restaurant, menu);
        }catch (Exception e){
        System.out.println("-- Menu already present! --");
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body("Menu already present");

    }

        return ResponseEntity.status(HttpStatus.OK)
                    .body(menu);
    }

    @GetMapping("/administrator")
    public ResponseEntity getMenusByRestaurant (@RequestParam String restaurantName){
        List<Menu> menuArrayList;
        Restaurant restaurant = restaurantService.getRestaurant(restaurantName);
        try {
            menuArrayList = (List<Menu>) restaurant.getMenuList();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("No menu had been found");
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(menuArrayList);
    }

    @PostMapping("/administrator/login")
    public ResponseEntity customerLogin(@RequestBody AdministratorDTO administratorDTO) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(administratorService.checkLogin(administratorDTO.getName(), administratorDTO.getPassword()));
        } catch (Exception e) {
            System.out.println("User had not been found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
        }
    }

    @GetMapping("/administrator/locations")
    public ResponseEntity getRestaurants(@RequestParam String administratorName) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(administratorService.getRestaurants(administratorName));
        } catch (Exception e) {
            System.out.println("User has no restaurants");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("-- User has no restaurants --");
        }
    }


}
