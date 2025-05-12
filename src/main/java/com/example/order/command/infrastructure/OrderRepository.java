package com.example.order.command.infrastructure;

import com.example.order.command.domain.OrderAggregate;
import org.springframework.data.jpa.repository.JpaRepository;

// command/infrastructure/OrderRepository.java
public interface OrderRepository extends JpaRepository<OrderAggregate, String> {}