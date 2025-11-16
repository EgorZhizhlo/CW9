package com.rehub.service;

import com.rehub.model.User;

import java.util.Optional;
import java.util.List;

public interface UserService {
    Optional<User> findByUsername(String username);
    User save(User user);
    Optional<User> findById(Long id);
    List<User> findAll();
    void deleteById(Long id);
    List<User> findByRole(User.Role role);
}
