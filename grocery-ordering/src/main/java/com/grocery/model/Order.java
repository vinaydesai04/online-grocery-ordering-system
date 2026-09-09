package com.grocery.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "customer_orders") // We change the table name because "order" is a reserved word in SQL
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Many orders can belong to one customer
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    // An order can have many items, and an item can be in many orders
    @ManyToMany
    @JoinTable(
        name = "order_items",
        joinColumns = @JoinColumn(name = "order_id"),
        inverseJoinColumns = @JoinColumn(name = "grocery_item_id")
    )
    private List<GroceryItem> groceryItems;

    private LocalDateTime orderDate;
    private Double totalPrice;
}