package com.example.easy_business_springboot.Model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long payment_id;
    private  String status;
    private  String amount;
    private  String paidDate;
    private  String control_number;

    @ManyToOne
    @JoinColumn(name = "licence_id")
    private License license;
}
