package com.springcraft.se.service;

import com.springcraft.se.domain.BookEntity;
import com.springcraft.se.mapper.BookMapper;
import com.springcraft.se.model.BookDto;
import com.springcraft.se.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class BookService {

    private final BookRepository repository;
    private final BookMapper bookMapper;

    public BookService(BookRepository repository,
                       BookMapper bookMapper) {
        this.repository = repository;
        this.bookMapper = bookMapper;
    }

    public List<BookDto> getBooks(int limit, int offset) {
        var bookEntityList = repository.findAll()
                .stream()
                .skip(offset)
                .limit(limit)
                .toList();
        log.info("Entity list: {}", bookEntityList);
        var bookDtoList = bookMapper.toDtoList(bookEntityList);
        log.info("DTO list: {}", bookDtoList);
        return bookDtoList;
    }

    @Transactional
    public BookDto addBook(String title, String author) {
        BookEntity bookEntity = BookEntity.builder()
                .title(title)
                .author(author)
                .build();
        bookEntity = repository.save(bookEntity);
        return bookMapper.toDto(bookEntity);
    }
}
