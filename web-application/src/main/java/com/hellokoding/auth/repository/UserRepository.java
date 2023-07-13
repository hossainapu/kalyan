package com.hellokoding.auth.repository;

import com.hellokoding.auth.model.db.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    User findFirstByEmail(String email);
}
