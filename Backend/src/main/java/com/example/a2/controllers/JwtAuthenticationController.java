package com.example.a2.controllers;

import com.example.a2.DTO.CustomerDTO;
import com.example.a2.config.JwtTokenUtil;
import com.example.a2.model.Customer;
//import com.example.a2.services.JwtUserDetailsService;
import com.example.a2.model.Status;
import com.example.a2.services.CustomerService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
//import com.example.a2.services.CustomerService;


@RestController
@CrossOrigin
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Lazy
    @Autowired
    private CustomerService customerService;

//    @Autowired
//    private JwtUserDetailsService userDetailsService;


    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody CustomerDTO customerDTO) throws Exception {

        authenticate(customerDTO.getName(), customerDTO.getPassword());

        final UserDetails userDetails = customerService
                .loadUserByUsername(customerDTO.getName());

        System.out.println(userDetails);

        final String token = jwtTokenUtil.generateToken(userDetails);

       // return ResponseEntity.ok(new JwtResponse(token));
        return ResponseEntity.status(HttpStatus.OK)
                .body(token);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody CustomerDTO customer) throws Exception {
        return ResponseEntity.ok(customerService.insertCustomer(new Customer(customer.getName(), customer.getPassword(), Status.ACCEPTED)));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}