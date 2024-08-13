package com.example.easy_business_springboot.Repository;

import com.example.easy_business_springboot.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepo  extends JpaRepository<Customer,Long> {

    Optional<Customer> findByUserID(Long userID);

}
