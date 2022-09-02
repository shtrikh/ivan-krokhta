package org.example.internetshopspring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.internetshopspring.dto.OrderDto;
import org.example.internetshopspring.service.OrderService;
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

import static org.example.internetshopspring.util.TestUtil.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {OrderController.class})
class OrderControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private OrderService orderService;

    @Test
    void findByIdSuccess() throws Exception {
        OrderDto orderDto = createOrderDto();

        when(orderService.findById(ORDER_ID)).thenReturn(orderDto);

        mockMvc.perform(get("/orders/" + ORDER_ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ORDER_ID));
    }

    @Test
    void createOrder() throws Exception {
        OrderDto orderDtoWithoutId = TestUtil.createOrderDtoWithoutId();
        OrderDto orderDto = TestUtil.createOrderDto();

        when(orderService.createOrder(any())).thenReturn(orderDto);

        mockMvc.perform(post("/orders")
                        .content(mapper.writeValueAsString(orderDtoWithoutId))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(PRODUCT_ID));
    }

    @Test
    void findAll() throws Exception {
        OrderDto orderDto1 = TestUtil.createOrderDto();
        OrderDto orderDto2 = TestUtil.createOrderDto(2L);
        OrderDto orderDto3 = TestUtil.createOrderDto(3L);

        List<OrderDto> orders = Arrays.asList(orderDto1, orderDto2, orderDto3);

        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.DEFAULT_DIRECTION, "id"));

        when(orderService.findAll(pageable)).thenReturn(new PageImpl<>(orders));

        mockMvc.perform(get("/orders?pageSize=10&pageNumber=0&sortValue=id"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements").value(3))
                .andExpect(jsonPath("$.content[0].id").value(ORDER_ID))
                .andExpect(jsonPath("$.content[1].id").value(2L))
                .andExpect(jsonPath("$.content[2].id").value(3L));

        verify(orderService, times(1)).findAll(pageable);
    }
}
