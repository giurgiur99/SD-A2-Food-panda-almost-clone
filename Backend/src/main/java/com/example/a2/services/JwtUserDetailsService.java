//package com.example.a2.services;
//
//import java.util.ArrayList;
//
//import com.example.a2.model.Customer;
//import com.example.a2.repositories.CustomerRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//public class JwtUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private CustomerRepo customerRepo;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        Customer customer = customerRepo.findByName(username);
//        if (customer == null) {
//            throw new UsernameNotFoundException("User not found with username: " + username);
//        }
//        return new org.springframework.security.core.userdetails.User(customer.getName(), customer.getPassword(),
//                new ArrayList<>());
//    }
//
//    public Customer save(Customer customer ) {
//        Customer newCustomer = new Customer();
//        newCustomer.setName(customer.getName());
//        newCustomer.setPassword(passwordEncoder.encode(customer.getPassword()));
//        return customerRepo.save(newCustomer);
//    }
//}