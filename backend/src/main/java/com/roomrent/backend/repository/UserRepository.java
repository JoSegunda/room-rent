package com.roomrent.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

import com.roomrent.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}
