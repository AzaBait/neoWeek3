package com.example.neobis.service.impl;

import com.example.neobis.dto.UserDto;
import com.example.neobis.entity.Role;
import com.example.neobis.entity.User;
import com.example.neobis.repository.UserRepo;
import com.example.neobis.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepo userRepo;
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    public Optional<User> findUserByEmail(String email) {
        return userRepo.findUserByEmail(email);
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<User> user = userRepo.findUserByEmail(email);
        if (user.isPresent()) {
//            logger.info("User found: {}", user.get().getEmail());   //
//            logger.info("Roles: {}", user.get().getRoles());
            Collection<? extends GrantedAuthority> authorities = mapRolesToAuthorities(user.get().getRoles());
 //           logger.info("Authorities: {}", authorities);   //
            return new org.springframework.security.core.userdetails.User(
                    user.get().getEmail(),user.get().getPassword(), mapRolesToAuthorities(user.get().getRoles()));
        }
        throw new UsernameNotFoundException(String.format("User with '%s' not found!", email));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
    @Override
    public ResponseEntity<User> save(User user) {
        Optional<User> userByEmail = userRepo.findUserByEmail(user.getEmail());
        if (userByEmail.isPresent()) {
            throw new IllegalStateException("This email " + user.getEmail() + " is already exists!");
        }
        User savedUser = userRepo.save(user);
        System.out.println("New user saved!");
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
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
        userInDB.setRoles(updatedUser.getRoles());

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
