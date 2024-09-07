package com.kstu.hackathon.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "Books")
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_seq")
    @SequenceGenerator(name = "book_seq", sequenceName = "book_seq")
    private Long id;
    private String title;
    @Column(length = 13)
    private String isbn;
    private int pubYear;
    private int total;
    private int available;
    private String digitalUrl;
}
