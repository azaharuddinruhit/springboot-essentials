package com.springcraft.se.api;

import com.springcraft.se.service.RabbitMQService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class TestController {

    private final RedisTemplate<String, Object> redisTemplate;
    private final RabbitMQService rabbitMQService;

    public TestController(RedisTemplate<String, Object> redisTemplate,
                          RabbitMQService rabbitMQService) {
        this.redisTemplate = redisTemplate;
        this.rabbitMQService = rabbitMQService;
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> saveToLocal(@RequestParam("file") MultipartFile file) {
        log.info(file.getOriginalFilename());
        return ResponseEntity.ok("");
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