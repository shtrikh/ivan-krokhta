package org.example.internetshopspring.mapper;

import org.example.internetshopspring.dto.OrderDto;
import org.example.internetshopspring.entity.Order;
import org.mapstruct.Mapper;

@Mapper
public interface OrderMapper {
    OrderDto toDto(Order order);

    Order toEntity(OrderDto orderDto);
}
