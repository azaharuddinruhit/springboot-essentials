package com.springcraft.se.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.springcraft.se.core.enums.UserType;

import java.util.Map;

/**
 * DTO for {@link com.springcraft.se.core.entity.UserEntity}
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