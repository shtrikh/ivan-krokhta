package org.example.internetshopspring.mapper;

import org.example.internetshopspring.dto.UserDto;
import org.example.internetshopspring.entity.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    UserDto toDto(User user);

    User toEntity(UserDto userDto);
}
