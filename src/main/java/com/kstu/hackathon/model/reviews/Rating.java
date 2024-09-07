package com.kstu.hackathon.model.reviews;


import com.kstu.hackathon.model.book.Book;
import com.kstu.hackathon.model.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Ratings", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "book_id"}))
public class Rating implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rate_get")
    @SequenceGenerator(name = "rate_get", sequenceName = "rate_get", allocationSize = 5)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column(name = "rate", nullable = false)
    private Integer rate;
}
