package org.example.internetshopspring.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.internetshopspring.dto.UserDto;
import org.example.internetshopspring.dto.group.OnCreate;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Internet Shop")
@RequestMapping("/users")
public interface UserApi {

    @Operation(summary = "Find all")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    Page<UserDto> findAll(@RequestParam("pageSize") int pageSize, @RequestParam("pageNumber") int pageNumber);

    @Operation(summary = "Create user")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    UserDto createUser(@RequestBody @Validated(OnCreate.class) UserDto userDto);

    @Operation(summary = "Find by id")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    UserDto findById(@PathVariable("id") Long id);
}
