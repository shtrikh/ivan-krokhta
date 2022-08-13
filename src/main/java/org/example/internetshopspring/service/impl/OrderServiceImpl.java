package org.example.internetshopspring.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.example.internetshopspring.dto.OrderDto;
import org.example.internetshopspring.entity.Order;
import org.example.internetshopspring.entity.Product;
import org.example.internetshopspring.entity.User;
import org.example.internetshopspring.enums.Status;
import org.example.internetshopspring.exception.EntityNotFoundException;
import org.example.internetshopspring.mapper.OrderMapper;
import org.example.internetshopspring.repository.OrderRepository;
import org.example.internetshopspring.repository.ProductRepository;
import org.example.internetshopspring.repository.UserRepository;
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
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Override
    public Page<OrderDto> findAll(Pageable pageable) {
        log.info("Order successfully selected");
        return orderRepository.findAll(pageable).map(orderMapper::toDto);
    }

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        Long userId = orderDto.getUser().getId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found by id: " + userId));
        Long productId = orderDto.getProduct().getId();
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("User not found by id: " + productId));
        Order order = orderMapper.toEntity(orderDto);
        order.setUser(user);
        order.setProduct(product);
        log.info("Order successfully created");
        return orderMapper.toDto(orderRepository.save(order));
    }

    @Override
    public OrderDto findById(Long id) {
        log.info("Order with id " + id + " successfully selected");
        return orderRepository.findById(id)
                .map(orderMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Order not found by id: " + id));
    }

    @Override
    public Page<OrderDto> findConfirmedOrders(Long userId, Pageable pageable) {
        log.info("Order dffffffffff2222");
        return orderRepository.findConfirmedOrders(userId, Status.BASKET, pageable).map(orderMapper::toDto);
    }
}
