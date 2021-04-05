package com.example.pawel.expense.repository;

import com.example.pawel.expense.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String Email);
    User findByUserName(String userName);
}
