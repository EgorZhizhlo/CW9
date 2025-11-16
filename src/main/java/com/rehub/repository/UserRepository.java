package com.rehub.repository;

import com.rehub.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    boolean existsByRole(User.Role role);

    List<User> findByRole(User.Role role);
}
