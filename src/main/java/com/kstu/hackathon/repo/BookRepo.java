package com.kstu.hackathon.repo;

import com.kstu.hackathon.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepo extends JpaRepository<Book, Long> {
    Book getBookByIsbn(String ISBN);
}
