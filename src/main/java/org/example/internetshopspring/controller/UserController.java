package org.example.internetshopspring.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.internetshopspring.api.UserApi;
import org.example.internetshopspring.dto.UserDto;
import org.example.internetshopspring.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Log4j2
public class UserController implements UserApi {

    private final UserService userService;

    @Override
    public Page<UserDto> findAll(int pageSize, int pageNumber) {
        Page<UserDto> users = userService.findAll(PageRequest.of(pageNumber, pageSize));
        log.info("Users successfully selected");
        return users;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        UserDto addedUser = userService.createUser(userDto);
        log.info("User successfully created");
        return addedUser;
    }

    @Override
    public UserDto findById(Long id) {
        UserDto user = userService.findById(id);
        log.info("User with id " + id + " successfully selected");
        return user;
    }
}
