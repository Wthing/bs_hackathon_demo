package com.kstu.hackathon.model.book;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Genres")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genre_seq")
    @SequenceGenerator(name = "genre_seq", sequenceName = "genre_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "genre")
    private String genre;

    @OneToMany(mappedBy = "genre")
    private List<GenresAndAuthorsToBooks> genresAndAuthorsToBooksList;
}
