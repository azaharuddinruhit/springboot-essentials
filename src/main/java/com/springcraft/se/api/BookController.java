package com.springcraft.se.api;

import com.springcraft.se.dto.BookDto;
import com.springcraft.se.enums.BookSortField;
import com.springcraft.se.enums.SortDirection;
import com.springcraft.se.service.BookService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @QueryMapping
    public List<BookDto> books(@Argument int limit, @Argument int offset) {
        return bookService.getBooks(limit, offset);
    }

    @QueryMapping
    public List<BookDto> searchBooks(
            @Argument int limit,
            @Argument int offset,
            @Argument String titleContains,
            @Argument String authorContains,
            @Argument BookSortField sortBy,
            @Argument SortDirection sortDirection
    ) {
        return bookService.getBooks(limit, offset, titleContains, authorContains,
                sortBy == null ? null : sortBy.name(),
                sortDirection == null ? null : sortDirection.name());
    }

    @MutationMapping
    public BookDto addBook(@Argument String title, @Argument String author) {
        return bookService.addBook(title, author);
    }
}
