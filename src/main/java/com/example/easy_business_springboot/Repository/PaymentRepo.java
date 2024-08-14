package com.example.easy_business_springboot.Repository;

import com.example.easy_business_springboot.Model.License;
import com.example.easy_business_springboot.Model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PaymentRepo  extends JpaRepository<Payment,Long> {



    Payment findByLicenseAndYears(License license, Long year);
}
