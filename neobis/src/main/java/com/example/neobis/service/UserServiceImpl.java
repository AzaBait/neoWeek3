package com.example.neobis.service;

import com.example.neobis.entity.User;
import com.example.neobis.repository.UserRepo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    @Override
    public void save(User user) {
        Optional<User> userByEmail = userRepo.findUserByEmail(user.getEmail());
        if (userByEmail.isPresent()) {
            throw new IllegalStateException("This email " + user.getEmail() + " is already exists!");
        }
        userRepo.save(user);
        System.out.println("New user saved!");
    }

    @Override
    public Optional<User> getById(Long id) {
        User user = userRepo.findById(id).orElseThrow(() -> new IllegalStateException("User with id " + id + " does not exist!"));
        return Optional.ofNullable(user);
    }

    @Override
    public User update(User updatedUser) {
        Long userId = updatedUser.getId();
        User userInDB = userRepo.findById(userId)
                .orElseThrow(() -> new IllegalStateException("User with id " + userId + " does not exist!"));

        userInDB.setName(updatedUser.getName());
        userInDB.setSurname(updatedUser.getSurname());
        userInDB.setEmail(updatedUser.getEmail());
        userInDB.setPhone(updatedUser.getPhone());

        userRepo.save(userInDB);

        System.out.println("User with id " + userId + " updated!");
        return userInDB;
    }

    @Override
    public void deleteUser(Long id) {
        boolean existsById = userRepo.existsById(id);
        if (!existsById) {
            throw new IllegalStateException("User with id " + id + " does not exist!");
        }
        userRepo.deleteById(id);
        System.out.println("User with id " + id + " is deleted!");
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();

    }
}
