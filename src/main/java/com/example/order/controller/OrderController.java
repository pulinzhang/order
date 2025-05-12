package com.example.order.controller;

import com.example.order.command.application.CreateOrderCommand;
import com.example.order.command.application.OrderCommandService;
import com.example.order.query.application.OrderQueryService;
import com.example.order.query.model.OrderView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST Controller for managing orders.
 * Provides endpoints for creating and retrieving orders.
 */
@RestController
@RequestMapping("/orders")
@Tag(name = "Order Management", description = "APIs for managing orders")
public class OrderController {
    @Resource
    private OrderCommandService commandService;
    @Resource
    private OrderQueryService queryService;

    /**
     * Creates a new order.
     *
     * @param command The order creation command containing customer ID and items
     * @return The ID of the created order
     */
    @Operation(
            summary = "Create a new order",
            description = "Creates a new order with the provided customer ID and items"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Order created successfully",
                    content = @Content(
                            mediaType = "text/plain",
                            schema = @Schema(implementation = String.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input data"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error"
            )
    })
    @PostMapping
    public ResponseEntity<String> createOrder(
            @Valid @RequestBody CreateOrderCommand command
    ) {
        String orderId = commandService.createOrder(command);
        return ResponseEntity.ok(orderId);
    }

    /**
     * Retrieves an order by its ID.
     *
     * @param orderId The ID of the order to retrieve
     * @return The order details
     */
    @Operation(
            summary = "Get order details",
            description = "Retrieves the details of an order by its ID"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Order found successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = OrderView.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Order not found"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error"
            )
    })
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderView> getOrder(
            @Parameter(description = "ID of the order to retrieve", required = true)
            @PathVariable String orderId
    ) {
        OrderView order = queryService.getOrderView(orderId);
        return ResponseEntity.ok(order);
    }
}