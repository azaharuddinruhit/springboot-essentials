package com.springcraft.se.service;

import com.springcraft.se.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Slf4j
public class OrderConsumer {

    @KafkaListener(
            topics = "${se.kafka.order-topic}",
            groupId = "${se.kafka.order-group}",
            containerFactory = "orderKafkaListenerContainerFactory"
    )
    public void consume(Order order) {
        log.info("📦 Received order: {}", order);
        if (LocalDate.now().isBefore(order.orderDate())) {
            log.info("Order date is in future!!!");
            throw new IllegalArgumentException("Future order date");
        }
    }
}
