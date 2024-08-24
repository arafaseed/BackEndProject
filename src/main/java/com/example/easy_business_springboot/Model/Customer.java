package com.example.easy_business_springboot.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Customer extends User {

    private String zan_Id;
    private String address;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<License> licenses;

}
