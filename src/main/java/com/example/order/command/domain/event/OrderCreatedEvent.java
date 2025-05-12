package com.example.order.command.domain.event;

import com.example.order.command.domain.OrderItem;

import java.util.List;

// command/domain/event/OrderCreatedEvent.java
public record OrderCreatedEvent(String orderId, String customerId, List<OrderItem> items) {}