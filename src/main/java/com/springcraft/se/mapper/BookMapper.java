package com.springcraft.se.mapper;

import com.springcraft.se.domain.BookEntity;
import com.springcraft.se.dto.BookDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookMapper {

//    @InheritInverseConfiguration
    BookDto toDto(BookEntity bookEntity);

    BookEntity toEntity(BookDto bookDto);

    List<BookDto> toDtoList(List<BookEntity> bookEntityList);

    List<BookEntity> toEntityList(List<BookDto> bookDtoList);

}
