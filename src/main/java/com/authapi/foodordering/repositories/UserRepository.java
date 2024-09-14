package com.authapi.foodordering.repositories;

import com.authapi.foodordering.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

  public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByFullName(String username);
  }
  
