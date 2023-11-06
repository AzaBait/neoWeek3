package com.example.neobis.controller;

import com.example.neobis.entity.User;
import com.example.neobis.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    @GetMapping("/list")
    public List<User> getAll() {
        return userService.getAllUsers();
    }
    @PostMapping("/save")
    public void saveUser(@Validated @RequestBody User user) {
        userService.save(user);
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {

        return userService.getById(id);
    }

    @PutMapping("/{id}")
    public User update(@Validated @PathVariable Long id, @RequestBody User user) {

        return userService.update(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable Long id) {
        userService.deleteUser(id);
    }
};
