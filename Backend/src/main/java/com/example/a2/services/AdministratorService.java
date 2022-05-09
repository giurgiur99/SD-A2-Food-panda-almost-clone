package com.example.a2.services;

import com.example.a2.model.Administrator;
import com.example.a2.model.Customer;
import com.example.a2.model.Menu;
import com.example.a2.model.Restaurant;
import com.example.a2.repositories.AdministratorRepo;
import com.example.a2.repositories.CustomerRepo;
import com.example.a2.repositories.RestaurantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.SecureRandom;
import java.util.List;

@Service
public class AdministratorService {
    @Autowired
    private AdministratorRepo administratorRepo;

    @Autowired
    private RestaurantRepo restaurantRepo;

    public static final String SECRET = "58FB308AB15653C86AD6DCF953CA8D00";
    private PasswordEncoder bcryptEncoder =  new BCryptPasswordEncoder(-1, new SecureRandom(SECRET.getBytes()));


    /**
     * Service used to insert a new administrator in database
     * Password automatically encrypted unsing bcrypt
     * @param administrator Administrator model saved in the db
     */
    public void insertAdministrator(Administrator administrator){
        administrator.setPassword(bcryptEncoder.encode(administrator.getPassword()));
        administratorRepo.save(administrator);
    }
    public Administrator getAdministrator(String name) {return administratorRepo.findByName(name);}

    /**
     * Service used to add a new restaurant which has the owner "administrator"
     * @param restaurant Restaurant selected to be added
     * @param administrator Administrator which own the restaurant
     */
    @Transactional
    public void addRestaurantToAdministrator(Restaurant restaurant, Administrator administrator){
        administrator.addRestaurant(restaurant);
        administratorRepo.save(administrator);
    }

    /**
     * Service used to check the credentials of a certain user
     * returs true or false based on the db entries
     * @param name
     * @param password
     * @return
     */
    public boolean checkLogin(String name, String password){
        Administrator administrator = administratorRepo.findByName(name);
        if(bcryptEncoder.matches(password, administrator.getPassword()))
            return true;
        else
            return false;

    }

    /**
     * Service used to retrieve a list of all available restaurants of a certain administrator
     * @param administratorName
     * @return
     */
    public List<Restaurant> getRestaurants(String administratorName){
        return administratorRepo.findByName(administratorName).getRestaurantList();
    }

    /**
     * Service used to retrive all menus of a certain restaurant
     * @param restaurantName
     * @return
     */
    public List<Menu> getRestaurantsMenu(String restaurantName){
        return restaurantRepo.findByName(restaurantName).getMenuList();
    }
}
