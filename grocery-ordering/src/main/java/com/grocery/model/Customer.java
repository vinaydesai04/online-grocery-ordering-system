package com.grocery.model; // Change 'com.grocery' to match your actual package name

import jakarta.persistence.*;
import lombok.Data;

@Data // Lombok annotation to automatically generate getters, setters, and constructors
@Entity // Tells Spring to map this class to a database table
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increments the ID
    private Long id;
    
    private String name;
    private String email;
    private String address;
    private String phone;
}