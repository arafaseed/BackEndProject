package com.example.easy_business_springboot.Repository;

import com.example.easy_business_springboot.Model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepo  extends JpaRepository<Payment,Long> {


}
