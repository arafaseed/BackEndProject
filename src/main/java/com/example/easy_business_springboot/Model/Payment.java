package com.example.easy_business_springboot.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

@Getter
@Data
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long payment_id;
    private String status;
    private Long amount;
    private String control_number;
    private String license_number;







    @ManyToOne
    @JoinColumn(name = "licence_id")
    private License license;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void findByPayment_id(Long payment_id) {
    }
}
