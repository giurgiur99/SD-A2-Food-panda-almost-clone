package com.example.a2.services;

import com.example.a2.model.Customer;
import com.example.a2.repositories.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepo customerRepo;

    public void insertCustomer(Customer customer){
        customerRepo.save(customer);
    }

    public boolean checkLogin(String name, String password){
        return customerRepo.findByName(name).getPassword().equals(password);
    }
}
