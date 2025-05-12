package com.example.order.command.infrastructure.eventListener;

import com.example.order.command.domain.OrderItem;
import com.example.order.command.domain.OrderStatus;
import com.example.order.command.domain.event.OrderCreatedEvent;
import com.example.order.query.infrastructure.OrderViewRepository;
import com.example.order.query.model.OrderItemView;
import com.example.order.query.model.OrderView;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderEventListener {
    private static final Logger logger = LoggerFactory.getLogger(OrderEventListener.class);
    private final OrderViewRepository orderViewRepository;

    public OrderEventListener(OrderViewRepository orderViewRepository) {
        this.orderViewRepository = orderViewRepository;
        logger.info("OrderEventListener initialized with OrderViewRepository");
    }

    // 监听订单创建事件，更新读模型
    @EventListener
    @Transactional(rollbackOn = Exception.class)
    public void handleOrderCreated(OrderCreatedEvent event) {
        logger.info("Received OrderCreatedEvent - Order ID: {}, Customer ID: {}", 
            event.orderId(), event.customerId());
        logger.debug("Processing order creation event with {} items", event.items().size());

        try {
            OrderView view = new OrderView();
            view.setOrderId(event.orderId());
            view.setCustomerId(event.customerId());
            view.setStatus(OrderStatus.CREATED.name());
            view.setItems(convertToItemViews(event.items()));
            
            logger.debug("Created OrderView object - Order ID: {}, Status: {}", 
                view.getOrderId(), view.getStatus());

            orderViewRepository.save(view);
            logger.info("Successfully saved OrderView to database - Order ID: {}", view.getOrderId());
            
        } catch (Exception e) {
            logger.error("Failed to process OrderCreatedEvent - Order ID: {}, Error: {}", 
                event.orderId(), e.getMessage(), e);
            throw e;
        }
    }

    private List<OrderItemView> convertToItemViews(List<OrderItem> items) {
        return items.stream()
                .map(item -> new OrderItemView(item.getProductId(), item.getQuantity(), item.getPrice()))
                .collect(Collectors.toList());
    }
}


