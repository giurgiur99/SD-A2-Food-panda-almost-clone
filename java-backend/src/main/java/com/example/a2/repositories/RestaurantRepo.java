package com.example.a2.repositories;

import com.example.a2.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepo extends JpaRepository<Restaurant, Long> {
    Restaurant findByName(String name);
    List<Restaurant> findAll();
    List<Restaurant> findAllByName(String name);
}
