package com.example.a2.repositories;

import com.example.a2.model.Food;
import com.example.a2.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepo extends JpaRepository<Food, Long> {
    Food findByName(String foodName);
}
