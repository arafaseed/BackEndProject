package com.example.easy_business_springboot.Repository;

import com.example.easy_business_springboot.Model.Customer;
import com.example.easy_business_springboot.Model.License;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LicenseRepo  extends JpaRepository<License,Long> {


}
