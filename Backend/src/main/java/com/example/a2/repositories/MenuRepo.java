package com.example.a2.repositories;

import com.example.a2.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepo extends JpaRepository<Menu, Long> {
    Menu findByName(String name);
}
