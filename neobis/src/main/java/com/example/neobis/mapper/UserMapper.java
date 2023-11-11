package com.example.neobis.mapper;

import com.example.neobis.dto.UserDto;
import com.example.neobis.entity.Role;
import com.example.neobis.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto entityToDto(User user);

    User dtoToEntity(UserDto userDto);

    List<UserDto> entitiesToDtos(List<User> users);
}
