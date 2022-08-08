package org.example.internetshopspring.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.internetshopspring.api.OrderApi;
import org.example.internetshopspring.dto.OrderDto;
import org.example.internetshopspring.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Log4j2
public class OrderController implements OrderApi {

    private final OrderService orderService;

    @Override
    public Page<OrderDto> findAll(int pageSize, int pageNumber) {
        Page<OrderDto> orders = orderService.findAll(PageRequest.of(pageNumber, pageSize));
        log.info("Orders successfully selected");
        return orders;
    }

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        OrderDto addedOrder = orderService.createOrder(orderDto);
        log.info("Order successfully created");
        return addedOrder;
    }

    @Override
    public OrderDto findById(Long id) {
        OrderDto order = orderService.findById(id);
        log.info("Order with id " + id + " successfully selected");
        return order;
    }
}
