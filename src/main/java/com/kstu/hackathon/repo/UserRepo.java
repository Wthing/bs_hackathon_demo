package com.kstu.hackathon.repo;

import com.kstu.hackathon.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

    Optional<User> findByPhoneNumber(String number);

    void deleteById(Long id);
}
