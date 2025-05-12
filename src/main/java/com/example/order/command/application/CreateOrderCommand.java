package com.example.order.command.application;

import com.example.order.command.domain.OrderItem;

import java.util.List;

// command/application/CreateOrderCommand.java
public record CreateOrderCommand(String customerId, List<OrderItem> items) {}

