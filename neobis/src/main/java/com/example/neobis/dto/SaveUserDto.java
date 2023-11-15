package com.example.neobis.dto;

import com.example.neobis.entity.Role;
import lombok.Data;

import java.util.Set;

@Data
public class SaveUserDto {

    private Long id;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String password;
    private Set<Role> roles;
}
