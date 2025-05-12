package com.example.order.query.model;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// query/model/OrderItemView.java
@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA需要无参构造
public class OrderItemView {
    private String productId;
    private int quantity;
    private double price;


}