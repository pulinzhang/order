package com.example.order.query.application;

import com.example.order.query.infrastructure.OrderViewRepository;
import com.example.order.query.model.OrderView;
import org.springframework.stereotype.Service;

// query/application/OrderQueryService.java
@Service
public class OrderQueryService {
    private final OrderViewRepository orderViewRepository;

    public OrderQueryService(OrderViewRepository orderViewRepository) {
        this.orderViewRepository = orderViewRepository;
    }

    // 查询订单视图
    public OrderView getOrderView(String orderId) {
        return orderViewRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }
}
