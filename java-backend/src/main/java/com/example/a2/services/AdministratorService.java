package com.example.a2.services;

import com.example.a2.model.Administrator;
import com.example.a2.model.Customer;
import com.example.a2.model.Restaurant;
import com.example.a2.repositories.AdministratorRepo;
import com.example.a2.repositories.CustomerRepo;
import com.example.a2.repositories.RestaurantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AdministratorService {
    @Autowired
    private AdministratorRepo administratorRepo;

    @Autowired
    private RestaurantRepo restaurantRepo;

    public void insertAdministrator(Administrator administrator){
        administratorRepo.save(administrator);
    }
    public Administrator getAdministrator(String name) {return administratorRepo.findByName(name);}

    @Transactional
    public void addRestaurantToAdministrator(Restaurant restaurant, Administrator administrator){
        administrator.addRestaurant(restaurant);
        administratorRepo.save(administrator);
    }
    public boolean checkLogin(String name, String password){
        return administratorRepo.findByName(name).getPassword().equals(password);
    }

    public List<Restaurant> getRestaurants(String administratorName){
        return administratorRepo.findByName(administratorName).getRestaurantList();
    }
}
