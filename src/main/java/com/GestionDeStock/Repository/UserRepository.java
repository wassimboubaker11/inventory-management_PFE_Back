package com.GestionDeStock.Repository;

import com.GestionDeStock.Entity.Role;
import com.GestionDeStock.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository <User, Integer> {

    Optional<User> findByEmail(String email);

    void  deleteByEmail(String email);

    User getUserByEmailAndRole(String email , Role role);
}