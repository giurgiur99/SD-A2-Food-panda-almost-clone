package com.example.a2.repositories;

import com.example.a2.model.Administrator;
import com.example.a2.model.Restaurant;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdministratorRepo extends JpaRepository<Administrator, Long> {
    Administrator findByName(String name);
}
