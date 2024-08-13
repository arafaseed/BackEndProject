package com.example.easy_business_springboot.Model;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class License {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long licence_id;
    private String business_name;
    private String created_date;
    private String endDate;
    private String building_location;
    private String amount;
    private String building_address;
    private String business_Type;
    private String region;
    private String status;
    private int number_ofYear;

    @ManyToOne
    @JoinColumn(name = "customerId")
    public Customer customer;

    @PrePersist
    void createdAt() {
        this.created_date = LocalDate.now().toString();
   }
}