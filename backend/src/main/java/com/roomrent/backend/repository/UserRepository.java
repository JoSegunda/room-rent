package com.roomrent.backend.repository;

import com.roomrent.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long>{
    boolean existsByEmail(String email);
}
