package com.example.a2.repositories;

import com.example.a2.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {
    Customer findByName(String name);
    Customer findByPassword(String password);

}
