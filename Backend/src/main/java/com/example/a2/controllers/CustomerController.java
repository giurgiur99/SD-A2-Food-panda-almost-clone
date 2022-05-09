package com.example.a2.controllers;

import com.example.a2.DTO.CustomerDTO;
import com.example.a2.DTO.FoodDTO;
import com.example.a2.DTO.RestaurantDTO;
import com.example.a2.config.JwtTokenUtil;
import com.example.a2.model.Customer;
import com.example.a2.model.Menu;
import com.example.a2.model.Restaurant;
import com.example.a2.model.Status;
import com.example.a2.services.CustomerService;
import com.example.a2.services.RestaurantService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/customer/add")
    public ResponseEntity addCustomer(@RequestBody CustomerDTO customerDTO){
        try {
            Customer customer = new Customer(customerDTO.getName(), customerDTO.getPassword(), Status.ACCEPTED);
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
            authenticate(customerDTO.getName(), customerDTO.getPassword());

            final UserDetails userDetails = customerService
                    .loadUserByUsername(customerDTO.getName());

            System.out.println(userDetails);

            final String token = jwtTokenUtil.generateToken(userDetails);

            Gson gson = new Gson();
            String json = gson.toJson(token);
            //JSONObject json = new JSONObject('{'  + "\"token\"" + ": \"" + token  + "\""+ '}');
            return ResponseEntity.status(HttpStatus.OK).body(json);
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("User had not been found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
        }
    }

    @PostMapping("/customer/welcome")
    public ResponseEntity welcome(@RequestHeader("Authorization") String token){
        try {
            String slicedToken = token.substring(7);
            String decodedUsername = jwtTokenUtil.getUsernameFromToken(slicedToken);
            return ResponseEntity.status(HttpStatus.OK).body("Hello user " + decodedUsername);
        } catch (Exception e) {
//            System.out.println("User had not been found");
            System.out.println(e);
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
    public ResponseEntity addOrder(@RequestHeader("Authorization") String token, @RequestBody FoodDTO foodDTO) throws MessagingException {

        try {
            String slicedToken = token.substring(7);
            String decodedUsername = jwtTokenUtil.getUsernameFromToken(slicedToken);
            customerService.order(customerService.findCustomer(decodedUsername), restaurantService.findFood(foodDTO.getName()));

            Properties prop = new Properties();
            prop.put("mail.smtp.host", "smtp.gmail.com");
            prop.put("mail.smtp.port", "465");
            prop.put("mail.smtp.ssl.enable", "true");
            prop.put("mail.smtp.auth", "true");

            String myAccountEmail = "testsdgiurgiu@gmail.com";
            String myPassword = "Parola123!";

            Session session = Session.getInstance(prop, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(myAccountEmail,myPassword);
                }
            });

                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(myAccountEmail));
                message.setRecipients(
                        Message.RecipientType.TO, new InternetAddress[]{new InternetAddress("giurgiur99@gmail.com")});
                message.setSubject("Order");

                String msg = "<h1 >Hello !</h1> " +
                    "<h2 >You can see your order details below</h2> " +
                    "<ol> " +
                    "<li>&nbsp;"
                        + foodDTO.toString() +"</li> " +
                    "</ol>" ;
//            String msgStyled = "This is my <b style='color:red;'>bold-red email</b> using JavaMailer";

                MimeBodyPart mimeBodyPart = new MimeBodyPart();
                mimeBodyPart.setContent(msg, "text/html; charset=utf-8");

                Multipart multipart = new MimeMultipart();
                multipart.addBodyPart(mimeBodyPart);

                message.setContent(multipart);

                Transport.send(message);

                return ResponseEntity.status(HttpStatus.OK).body(foodDTO);
            }catch (Exception e){
                System.out.println(e);
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e);
            }

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
