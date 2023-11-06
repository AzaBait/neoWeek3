package com.example.neobis.service;

import com.example.neobis.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void save(User user);

    Optional<User> getById(Long id);

    User update(User user);

    void deleteUser(Long id);

    List<User> getAllUsers();
}
