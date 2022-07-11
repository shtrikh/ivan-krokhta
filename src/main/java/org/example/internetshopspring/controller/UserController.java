package org.example.internetshopspring.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.internetshopspring.dto.UserDto;
import org.example.internetshopspring.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Log4j2
public class UserController {

    private final UserService userService;

    @GetMapping
    public Page<UserDto> findAll(@RequestParam("pageSize") int pageSize, @RequestParam("pageNumber") int pageNumber) {
        Page<UserDto> users = userService.findAll(PageRequest.of(pageNumber, pageSize));
        log.info("Users successfully selected");
        return users;
    }

    @PostMapping
    public UserDto createProduct(@RequestBody UserDto userDto) {
        UserDto addedUser = userService.createUser(userDto);
        log.info("User successfully created");
        return addedUser;
    }

    @GetMapping("/{id}")
    public UserDto findById(@PathVariable("id") Long id) {
        UserDto user = userService.findById(id);
        log.info("User with id " + id + " successfully selected");
        return user;
    }
}
