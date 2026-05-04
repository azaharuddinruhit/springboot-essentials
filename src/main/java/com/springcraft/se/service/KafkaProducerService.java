package com.springcraft.se.service;

import com.springcraft.se.model.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class KafkaProducerService {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private static final String TOPIC = "test-topic-order";

    public void sendOrder(Order order) {
        kafkaTemplate.send(TOPIC, order.orderId(), order);
        log.info("Sent order to Kafka: {}", order);
    }
}
