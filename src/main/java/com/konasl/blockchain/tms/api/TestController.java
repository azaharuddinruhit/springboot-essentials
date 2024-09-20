package com.konasl.blockchain.tms.api;

import com.konasl.blockchain.tms.core.service.RabbitMQService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class TestController {

    private final RedisTemplate<String, Object> redisTemplate;
    private final RabbitMQService rabbitMQService;

    public TestController(RedisTemplate<String, Object> redisTemplate,
                          RabbitMQService rabbitMQService) {
        this.redisTemplate = redisTemplate;
        this.rabbitMQService = rabbitMQService;
    }

    @PostMapping("/redis")
    public Map.Entry<String, Object> setData(@RequestBody Map.Entry<String, Object> entry) {
        redisTemplate.opsForValue().set(entry.getKey(), entry.getValue());
        return entry;
    }

    @GetMapping("/redis/{key}")
    public Object getData(@PathVariable String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @PostMapping("/rabbitmq")
    public void sendMessage(@RequestBody String message) {
        rabbitMQService.sendMessage(message);
    }
}