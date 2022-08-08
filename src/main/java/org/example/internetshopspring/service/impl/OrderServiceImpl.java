package org.example.internetshopspring.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.example.internetshopspring.dto.OrderDto;
import org.example.internetshopspring.exception.EntityNotFoundException;
import org.example.internetshopspring.mapper.OrderMapper;
import org.example.internetshopspring.repository.OrderRepository;
import org.example.internetshopspring.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public Page<OrderDto> findAll(Pageable pageable) {
        log.info("Order successfully selected");
        return orderRepository.findAll(pageable).map(orderMapper::toDto);
    }

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        log.info("Order successfully created");
        return orderMapper.toDto(orderRepository.save(orderMapper.toEntity(orderDto)));
    }

    @Override
    public OrderDto findById(Long id) {
        log.info("Order with id " + id + " successfully selected");
        return orderRepository.findById(id)
                .map(orderMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found by id: " + id));
    }
}
