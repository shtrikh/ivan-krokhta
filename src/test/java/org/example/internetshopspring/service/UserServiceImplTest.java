package org.example.internetshopspring.service;

import org.example.internetshopspring.dto.UserDto;
import org.example.internetshopspring.entity.User;
import org.example.internetshopspring.exception.EntityNotFoundException;
import org.example.internetshopspring.mapper.UserMapper;
import org.example.internetshopspring.repository.UserRepository;
import org.example.internetshopspring.service.impl.UserServiceImpl;
import org.example.internetshopspring.util.TestUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.example.internetshopspring.util.TestUtil.USER_ID;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMapper userMapper;

    @Test
    void findAll() {
        UserDto userDto1 = TestUtil.createUserDto();
        UserDto userDto2 = TestUtil.createUserDto(2L);
        UserDto userDto3 = TestUtil.createUserDto(3L);

        User user1 = TestUtil.createUser();
        User user2 = TestUtil.createUser(2L);
        User user3 = TestUtil.createUser(3L);

        when(userMapper.toDto(user1)).thenReturn(userDto1);
        when(userMapper.toDto(user2)).thenReturn(userDto2);
        when(userMapper.toDto(user3)).thenReturn(userDto3);

        List<User> users = Arrays.asList(user1, user2, user3);

        Pageable pageable = Pageable.unpaged();

        when(userRepository.findAll(pageable))
                .thenReturn(new PageImpl<>(users, pageable, users.size()));

        Page<UserDto> resultPageableUsers = userService.findAll(pageable);

        assertThat(resultPageableUsers.getContent(), hasItems(userDto1, userDto2, userDto3));

        verify(userRepository, times(1)).findAll(pageable);
    }

    @Test
    void findAllEmptyResult() {
        when(userRepository.findAll(ArgumentMatchers.any(Pageable.class))).thenReturn(new PageImpl<>(Collections.emptyList()));
        Page<UserDto> resultPageableUsers = userService.findAll(Pageable.unpaged());
        assertTrue(resultPageableUsers.isEmpty());
    }

    @Test
    void findByIdSuccess() {
        User user = TestUtil.createUser();
        when(userRepository.findById(USER_ID)).thenReturn(Optional.of(user));
        UserDto userDto = TestUtil.createUserDto();
        when(userMapper.toDto(user)).thenReturn(userDto);

        UserDto result = userService.findById(USER_ID);

        assertEquals(USER_ID, result.getId());
    }

    @Test
    void findByIdNotFound() {
        when(userRepository.findById(USER_ID)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> userService.findById(USER_ID));

    }

    @Test
    void createUser() {
        User testUser = TestUtil.createUser();
        UserDto testUserDto = TestUtil.createUserDto();
        when(userMapper.toDto(testUser)).thenReturn(testUserDto);
        when(userRepository.save(ArgumentMatchers.any())).thenReturn(testUser);

        UserDto userDto = userService.createUser(testUserDto);

        assertThat(userDto, allOf(
                hasProperty("name", equalTo(testUser.getName())),
                hasProperty("surname", equalTo(testUser.getSurname())),
                hasProperty("email", equalTo(testUser.getEmail()))
        ));
    }
}
