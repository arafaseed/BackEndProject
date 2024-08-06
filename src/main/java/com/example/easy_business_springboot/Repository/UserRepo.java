package com.example.easy_business_springboot.Repository;

import com.example.easy_business_springboot.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo  extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);
}
