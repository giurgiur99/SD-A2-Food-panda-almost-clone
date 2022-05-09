package com.example.a2.services;

import com.example.a2.model.Customer;
import com.example.a2.model.Food;
import com.example.a2.repositories.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;


@Service
public class CustomerService implements UserDetailsService {

    @Autowired
    private CustomerRepo customerRepo;

    //@Autowired
    public static final String SECRET = "58FB308AB15653C86AD6DCF953CA8D00";
    private PasswordEncoder bcryptEncoder =  new BCryptPasswordEncoder(-1, new SecureRandom(SECRET.getBytes()));

    /**
     * Service used to insert new customer in db
     * @param customer
     * @return
     */
    public Customer insertCustomer(Customer customer){

        customer.setPassword(bcryptEncoder.encode(customer.getPassword()));
        return customerRepo.save(customer);
    }

    /**
     * Service used to check credentials for a customer
     * @param name
     * @param password
     * @return true or false
     */
    public boolean checkLogin(String name, String password){
        Customer customer = customerRepo.findByName(name);
        if(bcryptEncoder.matches(password, customer.getPassword()))
             return true;
        else
            return false;
    }

    /**
     * Service used to p[lace a new order
     * @param customer
     * @param food
     */
    public void order(Customer customer, Food food){
        customer.addFood(food);
        customerRepo.save(customer);
    }


    public Customer findCustomer(String customerName){
        return  customerRepo.findByName(customerName);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Customer customer = customerRepo.findByName(username);
        if (customer == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(customer.getName(), customer.getPassword(),
                new ArrayList<>());
    }
}
