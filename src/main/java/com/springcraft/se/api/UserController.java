package com.springcraft.se.api;

import com.springcraft.se.dto.UserDTO;
import com.springcraft.se.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "User Controller", description = "It contains user related endpoints")
public class UserController {

    private final UserService userService;

    @GetMapping(version = "1.0")
    public List<UserDTO> getUsersV1() {
        log.info("Getting all users V1");
        return userService.getAllUsers();
    }

    @GetMapping(value = "/{userId}", version = "2.0")
    public List<UserDTO> getUsersV2(@PathVariable("userId") Long userId) {
        log.info("Getting all users V2");
        return userService.getAllUsers().stream()
                .filter(e -> Objects.equals(e.id(), userId))
                .toList();
    }

    @GetMapping(version = "3.0")
    public List<UserDTO> getUsersV3() {
        log.info("Getting all users V3");
        return userService.getAllUsers();
    }

    @PostMapping
    public UserDTO createUser(@RequestBody UserDTO dto) {
        log.info("User: {}", dto);
        return userService.saveUser(dto);
    }
}
