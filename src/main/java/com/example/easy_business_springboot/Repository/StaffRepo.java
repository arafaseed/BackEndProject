package com.example.easy_business_springboot.Repository;

import com.example.easy_business_springboot.Model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StaffRepo  extends JpaRepository<Staff,Long> {


    Optional<Staff> findByUserID(Long UserID);
}
