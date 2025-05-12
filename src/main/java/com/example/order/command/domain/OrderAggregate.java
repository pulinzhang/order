// command/domain/OrderAggregate.java
package com.example.order.command.domain;

import com.example.order.command.domain.event.OrderCreatedEvent;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import java.util.List;

@Entity
@Getter
public class OrderAggregate {
    @Id
    private String orderId;
    private String customerId;
    private OrderStatus status;

    @ElementCollection
    private List<OrderItem> items;

    // Aggregate root method: create order
    public static OrderAggregate create(String orderId, String customerId, List<OrderItem> items) {
        OrderAggregate order = new OrderAggregate();
        order.orderId = orderId;
        order.customerId = customerId;
        order.items = items;
        order.status = OrderStatus.CREATED;
        return order;
    }

    // Domain event: order created event
    public OrderCreatedEvent createEvent() {
        return new OrderCreatedEvent(this.orderId, this.customerId, this.items);
    }
}





