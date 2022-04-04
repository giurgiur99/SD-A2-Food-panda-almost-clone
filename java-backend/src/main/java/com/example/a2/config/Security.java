package com.example.a2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.SecureRandom;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class Security extends WebSecurityConfigurerAdapter {
    private static final String SECRET = "58FB308AB15653C86AD6DCF953CA8D00";
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(-1, new SecureRandom(SECRET.getBytes()));
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests().

                anyRequest().permitAll()

                .and().cors().and()

                .csrf().disable();;





    }



}
