package com.example.springdemo.repositories;

import com.example.springdemo.models.Ad;
import com.example.springdemo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
