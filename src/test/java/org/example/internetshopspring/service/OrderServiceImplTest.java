package org.example.internetshopspring.service;

import org.example.internetshopspring.dto.OrderDto;
import org.example.internetshopspring.entity.Order;
import org.example.internetshopspring.enums.Status;
import org.example.internetshopspring.exception.EntityNotFoundException;
import org.example.internetshopspring.mapper.OrderMapper;
import org.example.internetshopspring.repository.OrderRepository;
import org.example.internetshopspring.service.impl.OrderServiceImpl;
import org.example.internetshopspring.util.TestUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.example.internetshopspring.util.TestUtil.ORDER_ID;
import static org.example.internetshopspring.util.TestUtil.USER_ID;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    @InjectMocks
    private OrderServiceImpl orderService;

    @Mock
    private OrderRepository orderRepository;
    @Mock
    private OrderMapper orderMapper;

    @Test
    void findByIdSuccess() {
        Order order = TestUtil.createOrder();
        OrderDto orderDto = TestUtil.createOrderDto();

        when(orderRepository.findById(ORDER_ID)).thenReturn(Optional.of(order));
        when(orderMapper.toDto(order)).thenReturn(orderDto);

        OrderDto result = orderService.findById(ORDER_ID);

        assertEquals(ORDER_ID, result.getId());
    }

    @Test
    void findByIdNotFound() {
        when(orderRepository.findById(ORDER_ID)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> orderService.findById(ORDER_ID));
    }

    @Test
    void createOrder() {
        Order testOrder = TestUtil.createOrder();
        OrderDto testOrderDto = TestUtil.createOrderDto();

        when(orderMapper.toDto(testOrder)).thenReturn(testOrderDto);
        when(orderRepository.save(ArgumentMatchers.any())).thenReturn(testOrder);

        OrderDto orderDto = orderService.createOrder(testOrderDto);

        assertThat(orderDto, allOf(
                hasProperty("id", equalTo(testOrder.getId())),
                hasProperty("status", equalTo(testOrder.getStatus())),
                hasProperty("user", equalTo(testOrder.getUser()))
        ));
    }

    @Test
    void findConfirmedOrders() {
        Order confirmedOrder = TestUtil.createOrder();
        confirmedOrder.setStatus(Status.CONFIRMED);

        OrderDto confirmedOrderDto = TestUtil.createOrderDto();
        confirmedOrderDto.setStatus(Status.CONFIRMED);

        Pageable pageable = Pageable.unpaged();

        when(orderMapper.toDto(confirmedOrder)).thenReturn(confirmedOrderDto);
        when(orderRepository.findConfirmedOrders(USER_ID, Status.BASKET, pageable))
                .thenReturn(new PageImpl<>(Arrays.asList(confirmedOrder)));

        Page<OrderDto> result = orderService.findConfirmedOrders(USER_ID, pageable);

        assertEquals(1, result.getTotalElements());
        assertEquals(Status.CONFIRMED, result.getContent().get(0).getStatus());
    }

    @Test
    void findAll() {
        OrderDto orderDto1 = TestUtil.createOrderDto();
        OrderDto orderDto2 = TestUtil.createOrderDto(2L);
        OrderDto orderDto3 = TestUtil.createOrderDto(3L);

        Order order1 = TestUtil.createOrder();
        Order order2 = TestUtil.createOrder(2L);
        Order order3 = TestUtil.createOrder(3L);

        when(orderMapper.toDto(order1)).thenReturn(orderDto1);
        when(orderMapper.toDto(order2)).thenReturn(orderDto2);
        when(orderMapper.toDto(order3)).thenReturn(orderDto3);

        List<Order> orders = Arrays.asList(order1, order2, order3);

        Pageable pageable = Pageable.unpaged();

        when(orderRepository.findAll(pageable))
                .thenReturn(new PageImpl<>(orders, pageable, orders.size()));

        Page<OrderDto> resultPageableOrders = orderService.findAll(pageable);

        assertThat(resultPageableOrders.getContent(), hasItems(orderDto1, orderDto2, orderDto3));

        verify(orderRepository, times(1)).findAll(pageable);
    }

    @Test
    void findAllEmptyResult() {
        when(orderRepository.findAll(ArgumentMatchers.any(Pageable.class))).thenReturn(new PageImpl<>(Collections.emptyList()));
        Page<OrderDto> resultPageableUsers = orderService.findAll(Pageable.unpaged());
        assertTrue(resultPageableUsers.isEmpty());
    }
}
