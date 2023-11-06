package com.example.neobis.service.impl;

import com.example.neobis.dto.UserDto;
import com.example.neobis.entity.User;
import com.example.neobis.repository.UserRepo;
import com.example.neobis.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    @Override
    public ResponseEntity<User> save(User user) {
        Optional<User> userByEmail = userRepo.findUserByEmail(user.getEmail());
        if (userByEmail.isPresent()) {
            throw new IllegalStateException("This email " + user.getEmail() + " is already exists!");
        }
        userRepo.save(user);
        System.out.println("New user saved!");
        return ResponseEntity.status(HttpStatus.CREATED).body(userRepo.save(user));
    }

    @Override
    public Optional<User> getById(Long id) {
        User user = userRepo.findById(id).orElseThrow(() -> new IllegalStateException("User with id " + id + " does not exist!"));
        return Optional.ofNullable(user);
    }

    @Override
    public ResponseEntity<User> update(Long userId, UserDto updatedUser) {
        User userInDB = userRepo.findById(userId)
                .orElseThrow(() -> new IllegalStateException("User with id " + userId + " does not exist!"));
        userInDB.setName(updatedUser.getName());
        userInDB.setSurname(updatedUser.getSurname());
        userInDB.setEmail(updatedUser.getEmail());
        userInDB.setPhone(updatedUser.getPhone());

        userRepo.save(userInDB);

        System.out.println("User with id " + userId + " updated!");
        return ResponseEntity.ok(userInDB);
    }

    @Override
    public ResponseEntity<String> deleteUser(Long id) {
        if (userRepo.findById(id).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with id " + id + " does not exist!");
        }
        userRepo.deleteById(id);
        return ResponseEntity.ok("User with id " + id + " is deleted!");
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();

    }
}
