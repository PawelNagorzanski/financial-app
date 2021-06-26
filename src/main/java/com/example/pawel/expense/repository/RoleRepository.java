package com.example.pawel.expense.repository;

import com.example.pawel.expense.model.Role;
import com.example.pawel.expense.model.RoleName;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}