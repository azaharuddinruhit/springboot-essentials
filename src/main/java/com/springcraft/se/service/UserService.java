package com.springcraft.se.service;

import com.springcraft.se.domain.UserEntity;
import com.springcraft.se.dto.UserDTO;
import com.springcraft.se.mapper.UserMapper;
import com.springcraft.se.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@NullMarked  // Everything non-null by default
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @Nullable
    public UserDTO saveUser(UserDTO dto) {
        UserEntity entity = userMapper.toEntity(dto);
        return userMapper.toDto(userRepository.save(entity));
    }
}
