package com.kstu.hackathon.model.book;


import com.kstu.hackathon.model.reviews.Rating;
import com.kstu.hackathon.model.reviews.Review;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name = "Books")
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_seq")
    @SequenceGenerator(name = "book_seq", sequenceName = "book_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "isbn", length = 13)
    private String isbn;

    @Column(name = "pub_year")
    private Integer pubYear;

    @Column(name = "total")
    private Integer total;

    @Column(name = "available")
    private Integer available;

    @Column(name = "digital_url")
    private String digitalUrl;

    @OneToMany(mappedBy = "book")
    private List<GenresAndAuthorsToBooks> genresAndAuthorsToBooksList;

    @OneToMany(mappedBy = "book")
    private List<Rating> ratingList;

    @OneToMany(mappedBy = "book")
    private List<Review> reviewList;
}
