package com.example.easy_business_springboot.Repository;

import com.example.easy_business_springboot.Model.License;
import com.example.easy_business_springboot.Model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface PaymentRepo  extends JpaRepository<Payment,Long> {



    @Query("SELECT p FROM Payment p WHERE p.license.licence_id = :license_id")
    Optional<Payment> findByLicenseId(@Param("license_id") Long license_id);
}

