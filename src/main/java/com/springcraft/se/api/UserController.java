package com.springcraft.se.api;

import com.springcraft.se.core.dto.UserDTO;
import com.springcraft.se.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserDTO> getUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public UserDTO createUser(@RequestBody UserDTO dto) {
        log.info("User: {}", dto);
        return userService.saveUser(dto);
    }
}
