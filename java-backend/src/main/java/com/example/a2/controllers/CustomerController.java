package com.example.a2.controllers;

import com.example.a2.DTO.CustomerDTO;
import com.example.a2.DTO.FoodDTO;
import com.example.a2.DTO.RestaurantDTO;
import com.example.a2.model.Customer;
import com.example.a2.model.Menu;
import com.example.a2.model.Restaurant;
import com.example.a2.model.Status;
import com.example.a2.services.CustomerService;
import com.example.a2.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping("/customer/add")
    public ResponseEntity addCustomer(@RequestBody CustomerDTO customerDTO){
        try {
            Customer customer = new Customer(customerDTO.getName(), customerDTO.getPassword(), Status.ACCEPTED);
           // customer.encrypt();
            customerService.insertCustomer(customer);
            return ResponseEntity.status(HttpStatus.OK).body(customerDTO);
        }catch (Exception e){
            System.out.println("User already added");
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("-- User already present! --");
        }
    }


    @PostMapping("/customer/login")
    public ResponseEntity customerLogin(@RequestBody CustomerDTO customerDTO) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(customerService.checkLogin(customerDTO.getName(), customerDTO.getPassword()));
        } catch (Exception e) {
            System.out.println("User had not been found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
        }
    }

    @GetMapping("/customer/explore")
    public ResponseEntity exploreRestaurants(){
        ArrayList<Restaurant> restaurantArrayList = new ArrayList<>();
        try{
            restaurantArrayList = (ArrayList<Restaurant>) restaurantService.getRestaurants();
            return ResponseEntity.status(HttpStatus.OK).body(restaurantArrayList);
        }catch (Exception e){
            System.out.println("-- No restaurants left to explore! --");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("-- No restaurants left to explore! --");
        }
    }

    @GetMapping("/customer/explore/menu")
    public ResponseEntity exploreMenuByRestaurant(@RequestParam String restaurantName){
        try{
            List<Menu> menuArrayList=  restaurantService.getRestaurant(restaurantName).getMenuList();
            return ResponseEntity.status(HttpStatus.OK).body(menuArrayList);
        }catch (Exception e){
            System.out.println("-- Restaurant not found! --");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("-- Restaurant not found! --");
        }
    }
    @PostMapping("/customer/order")
    public ResponseEntity addOrder(@RequestParam String customer, @RequestBody FoodDTO foodDTO){
//        try {
            customerService.order(customerService.findCustomer(customer), restaurantService.findFood(foodDTO.getName()));
            return ResponseEntity.status(HttpStatus.OK).body(foodDTO);
//        }catch (Exception e){
//            System.out.println("-- Error --");
//            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("-- Error --");
//        }
   }

}
