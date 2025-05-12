package com.example.order.command.domain;

import jakarta.persistence.Embeddable;
import lombok.Data;

// command/domain/OrderItem.java
@Embeddable
@Data
public class OrderItem {
    private String productId;
    private int quantity;
    private double price;
}