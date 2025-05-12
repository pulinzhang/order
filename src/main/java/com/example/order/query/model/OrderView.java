package com.example.order.query.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;

// query/model/OrderView.java
@Entity
@Data
@Table(name = "order_view")
public class OrderView {
    @Id
    private String orderId;
    private String customerId;
    private String status;

    @ElementCollection
    private List<OrderItemView> items;
}





