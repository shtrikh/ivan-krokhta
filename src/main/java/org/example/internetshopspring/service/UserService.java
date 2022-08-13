package org.example.internetshopspring.service;

import org.example.internetshopspring.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    Page<UserDto> findAll(Pageable pageable);
    UserDto createUser(UserDto userDto);
    UserDto findById(Long id);
}
