package com.example.pawel.expense.repository;

import com.example.pawel.expense.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
