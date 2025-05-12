package com.example.order.command.application;

import com.example.order.command.domain.OrderAggregate;
import com.example.order.command.infrastructure.OrderRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.UUID;

// command/application/OrderCommandService.java
@Service
@Transactional
public class OrderCommandService {
    private static final Logger logger = LoggerFactory.getLogger(OrderCommandService.class);
    private final OrderRepository orderRepository;
    private final ApplicationEventPublisher eventPublisher;

    public OrderCommandService(OrderRepository orderRepository, ApplicationEventPublisher eventPublisher) {
        this.orderRepository = orderRepository;
        this.eventPublisher = eventPublisher;
    }

    // Handle create order command
    public String createOrder(CreateOrderCommand command) {
        if (command == null) {
            throw new IllegalArgumentException("Command cannot be null");
        }
        if (command.customerId() == null || command.customerId().trim().isEmpty()) {
            throw new IllegalArgumentException("Customer ID cannot be empty");
        }
        if (command.items() == null || command.items().isEmpty()) {
            throw new IllegalArgumentException("Order must contain at least one item");
        }
        logger.info("Starting order creation process for customer: {}", command.customerId());
        logger.debug("Order creation request details - Customer ID: {}, Number of items: {}", 
            command.customerId(), command.items().size());
        
        String orderId = UUID.randomUUID().toString();
        logger.debug("Generated order ID: {}", orderId);
        
        OrderAggregate order = OrderAggregate.create(orderId, command.customerId(), command.items());
        logger.debug("Order aggregate created with status: {}", order.getStatus());
        
        orderRepository.save(order);
        logger.info("Order saved to database successfully - Order ID: {}", orderId);
        
        // Publish domain event
        eventPublisher.publishEvent(order.createEvent());
        logger.info("Order created event published - Order ID: {}, Status: {}", 
            orderId, order.getStatus());
        
        return orderId;
    }
}

