package com.example.easy_business_springboot.Model;

import com.example.easy_business_springboot.Model.Enum.Role;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long userID;
    @Column(unique = true, nullable = false)
    private String name;
    private String username;
    private String password;
    private String gender;
    private String phone;

    //
    @Enumerated(EnumType.STRING)
    private Role role;

}
