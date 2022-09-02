package org.example.internetshopspring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.internetshopspring.dto.UserDto;
import org.example.internetshopspring.service.UserService;
import org.example.internetshopspring.util.TestUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.example.internetshopspring.util.TestUtil.USER_ID;
import static org.example.internetshopspring.util.TestUtil.createUserDto;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {UserController.class})
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private UserService userService;

    @Test
    void findByIdSuccess() throws Exception {
        UserDto userDto = createUserDto();

        when(userService.findById(USER_ID)).thenReturn(userDto);

        mockMvc.perform(get("/users/" + USER_ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(USER_ID));
    }

    @Test
    void findAll() throws Exception {
        UserDto userDto1 = TestUtil.createUserDto();
        UserDto userDto2 = TestUtil.createUserDto(2L);
        UserDto userDto3 = TestUtil.createUserDto(3L);

        List<UserDto> users = Arrays.asList(userDto1, userDto2, userDto3);

        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.DEFAULT_DIRECTION, "id"));

        when(userService.findAll(pageable)).thenReturn(new PageImpl<>(users));

        mockMvc.perform(get("/users?pageSize=10&pageNumber=0&sortValue=id"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements").value(3))
                .andExpect(jsonPath("$.content[0].id").value(USER_ID))
                .andExpect(jsonPath("$.content[1].id").value(2L))
                .andExpect(jsonPath("$.content[2].id").value(3L));

        verify(userService, times(1)).findAll(pageable);
    }

    @Test
    void createUser() throws Exception {
        UserDto userDtoWithoutId = TestUtil.createUserDtoWithoutId();
        UserDto userDto = TestUtil.createUserDto();

        when(userService.createUser(any())).thenReturn(userDto);

        mockMvc.perform(post("/users")
                        .content(mapper.writeValueAsString(userDtoWithoutId))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(USER_ID));
    }
}
