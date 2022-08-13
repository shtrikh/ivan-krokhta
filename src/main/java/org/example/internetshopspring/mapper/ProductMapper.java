package org.example.internetshopspring.mapper;

import org.example.internetshopspring.dto.ProductDto;
import org.example.internetshopspring.entity.Product;
import org.mapstruct.Mapper;

@Mapper
public interface ProductMapper {
    ProductDto toDto(Product product);

    Product toEntity(ProductDto productDto);
}
