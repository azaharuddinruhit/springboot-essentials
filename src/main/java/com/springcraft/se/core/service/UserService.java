package com.springcraft.se.core.service;

import com.springcraft.se.core.dto.UserDTO;
import com.springcraft.se.core.entity.UserEntity;
import com.springcraft.se.core.mapper.UserMapper;
import com.springcraft.se.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    public UserDTO saveUser(UserDTO dto) {
        UserEntity entity = userMapper.toEntity(dto);
        return userMapper.toDto(userRepository.save(entity));
    }
}
