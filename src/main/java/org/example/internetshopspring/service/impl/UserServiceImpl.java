package org.example.internetshopspring.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.example.internetshopspring.dto.UserDto;
import org.example.internetshopspring.exception.EntityNotFoundException;
import org.example.internetshopspring.mapper.UserMapper;
import org.example.internetshopspring.repository.UserRepository;
import org.example.internetshopspring.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public Page<UserDto> findAll(Pageable pageable) {
        log.info("Users successfully selected");
        return userRepository.findAll(pageable).map(userMapper::toDto);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        log.info("User successfully created");
        return userMapper.toDto(userRepository.save(userMapper.toEntity(userDto)));
    }

    @Override
    public UserDto findById(Long id) {
        log.info("User with id " + id + " successfully selected");
        return userRepository.findById(id)
                .map(userMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found by id: " + id));
    }
}
