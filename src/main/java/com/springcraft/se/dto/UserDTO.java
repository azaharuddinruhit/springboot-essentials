package com.springcraft.se.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.springcraft.se.domain.UserEntity;
import com.springcraft.se.enums.UserType;

import java.util.Map;

/**
 * DTO for {@link UserEntity}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record UserDTO(
        Long id,
        String name,
        String email,
        String dob, // formatted as "yyyy-MM-dd"
        Map<UserType, String> userTypeStringMap
) {
}