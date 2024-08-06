package com.example.easy_business_springboot.Model;

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
    private String building_location;
    private String building_address;
    private String business_Type;
    private String region;
    private String status;
    @ManyToOne
    @JoinColumn(name = "customerId")
    public Customer customer;
}
