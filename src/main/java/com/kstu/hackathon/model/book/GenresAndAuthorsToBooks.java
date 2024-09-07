package com.kstu.hackathon.model.book;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "GenresAndAuthorsToBooks")
public class GenresAndAuthorsToBooks {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_and_auth_to_book_seq")
    @SequenceGenerator(name = "gen_and_auth_to_book_seq", sequenceName = "gen_and_auth_to_book_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;
}
