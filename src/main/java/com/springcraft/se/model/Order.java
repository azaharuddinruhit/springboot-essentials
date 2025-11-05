package com.springcraft.se.model;

import com.springcraft.se.enums.OrderStatus;

import java.time.LocalDate;

public record Order(
        String orderId,
        String customerName,
        double amount,
        LocalDate orderDate,
        OrderStatus status
) {
}
