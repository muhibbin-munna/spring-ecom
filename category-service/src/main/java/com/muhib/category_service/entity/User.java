package com.muhib.category_service.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "User_Table")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;

    @Column(length = 60)
    private String password;

    private String role;
}
