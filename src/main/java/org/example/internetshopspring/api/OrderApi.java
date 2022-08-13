package org.example.internetshopspring.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.internetshopspring.dto.OrderDto;
import org.example.internetshopspring.dto.group.OnCreate;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Internet Shop")
@RequestMapping("/orders")
public interface OrderApi {

    @Operation(summary = "Find all")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    Page<OrderDto> findAll(@RequestParam("pageSize") int pageSize, @RequestParam("pageNumber") int pageNumber,
                           @RequestParam("sortValue") String sortValue);

    @Operation(summary = "Create order")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    OrderDto createOrder(@RequestBody @Validated(OnCreate.class) OrderDto orderDto);

    @Operation(summary = "Find by id")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    OrderDto findById(@PathVariable("id") Long id);

    @Operation(summary = "Find confirmed")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/confirmed/{userId}")
    Page<OrderDto> findConfirmedOrder(@PathVariable("userId") Long userId,
                                      @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                      @RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber);
}
