package com.springcraft.se.core.mapper;

import com.springcraft.se.core.dto.UserDTO;
import com.springcraft.se.core.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface UserMapper {

    DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Mapping(source = "dateOfBirth", target = "dob", dateFormat = "yyyy-MM-dd")
    UserDTO toDto(UserEntity user);

    @Mapping(source = "dob", target = "dateOfBirth")
    UserEntity toEntity(UserDTO dto);

    default String map(LocalDate date) {
        return date != null ? FORMATTER.format(date) : null;
    }

    default LocalDate map(String date) {
        return date != null ? LocalDate.parse(date, FORMATTER) : null;
    }
}
