package org.example.internetshopspring.service;

import org.example.internetshopspring.dto.OrderDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    Page<OrderDto> findAll(Pageable pageable);
    OrderDto createOrder(OrderDto orderDto);
    OrderDto findById(Long id);
    Page<OrderDto> findConfirmedOrders(Long userId,Pageable pageable);
}
