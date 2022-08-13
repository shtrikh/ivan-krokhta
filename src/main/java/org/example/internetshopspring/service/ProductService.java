package org.example.internetshopspring.service;

import org.example.internetshopspring.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Page<ProductDto> findAll(Pageable pageable);
    ProductDto createProduct(ProductDto productDto);
    ProductDto findById(Long id);
}
