package com.example.order.query.infrastructure;

import com.example.order.query.model.OrderView;
import org.springframework.data.jpa.repository.JpaRepository;

// query/infrastructure/OrderViewRepository.java
public interface OrderViewRepository extends JpaRepository<OrderView, String> {}