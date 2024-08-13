package com.example.easy_business_springboot.Repository;

import com.example.easy_business_springboot.Model.License;
import com.example.easy_business_springboot.Model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PaymentRepo  extends JpaRepository<Payment,Long> {
//    Optional<Payment> findTopByOrderByEndDateDesc();
//    @Query(value = "SELECT end_date FROM payment WHERE licence_id =?1 ORDER BY end_date DESC LIMIT 1", nativeQuery = true)
//    Payment getEndLastByLicenceId(Long licence_id);

    Payment findByLicenseAndYears(License license, Long year);
}
