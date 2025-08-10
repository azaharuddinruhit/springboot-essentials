package com.springcraft.se.domain;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "BOOK")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "AUTHOR")
    private String author;
}
