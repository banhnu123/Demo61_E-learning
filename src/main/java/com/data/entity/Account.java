package com.data.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class Account {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    private String fullName;
    private String role;
}
