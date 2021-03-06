package com.example.pawel.expense.repository;

import com.example.pawel.expense.model.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    
    

    Optional<User> findByUsernameOrEmail(String username, String email);

    List<User> findByIdIn(List<Long> id);

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
