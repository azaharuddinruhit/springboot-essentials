package com.springcraft.se.repository;

import com.springcraft.se.domain.BookEntity;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecification {

    public static Specification<BookEntity> titleContains(String keyword) {
        return (root, query, cb) -> keyword == null
                ? null
                : cb.like(cb.lower(root.get("title")), "%" + keyword.toLowerCase() + "%");
    }

    public static Specification<BookEntity> authorContains(String keyword) {
        return (root, query, cb) -> keyword == null
                ? null
                : cb.like(cb.lower(root.get("author")), "%" + keyword.toLowerCase() + "%");
    }
}
