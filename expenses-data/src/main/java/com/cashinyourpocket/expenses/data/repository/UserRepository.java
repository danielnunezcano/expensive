package com.cashinyourpocket.expenses.data.repository;

import java.util.List;

import com.cashinyourpocket.expenses.data.model.UserJpa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserJpa, Integer> {

  List<UserJpa> findByUsername(String username);

}
