package com.example.a2.services;

import com.example.a2.DTO.FoodDTO;
import com.example.a2.model.Customer;
import com.example.a2.model.Food;
import com.example.a2.model.Restaurant;
import com.example.a2.repositories.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepo customerRepo;
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void insertCustomer(Customer customer){

        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customerRepo.save(customer);
    }

    public boolean checkLogin(String name, String password){
        Customer customer = customerRepo.findByName(name);
        if(passwordEncoder.matches(password, customer.getPassword()))
             return true;
        else
            return false;
    }

    public void order(Customer customer, Food food){
        customer.addFood(food);
        customerRepo.save(customer);
    }

    public Customer findCustomer(String customerName){
        return  customerRepo.findByName(customerName);
    }
}
