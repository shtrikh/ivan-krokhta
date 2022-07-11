package org.example.internetshopspring.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.internetshopspring.dto.OrderDto;
import org.example.internetshopspring.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@Log4j2
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public Page<OrderDto> findAll(@RequestParam("pageSize") int pageSize, @RequestParam("pageNumber") int pageNumber) {
        Page<OrderDto> orders = orderService.findAll(PageRequest.of(pageNumber, pageSize));
        log.info("Orders successfully selected");
        return orders;
    }

    @PostMapping
    public OrderDto createProduct(@RequestBody OrderDto orderDto) {
        OrderDto addedOrder = orderService.createOrder(orderDto);
        log.info("Order successfully created");
        return addedOrder;
    }

    @GetMapping("/{id}")
    public OrderDto findById(@PathVariable("id") Long id) {
        OrderDto order = orderService.findById(id);
        log.info("Order with id " + id + " successfully selected");
        return order;
    }
}
