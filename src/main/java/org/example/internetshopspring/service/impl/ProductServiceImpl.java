package org.example.internetshopspring.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.example.internetshopspring.dto.ProductDto;
import org.example.internetshopspring.exception.EntityNotFoundException;
import org.example.internetshopspring.mapper.ProductMapper;
import org.example.internetshopspring.repository.ProductRepository;
import org.example.internetshopspring.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    @Override
    public Page<ProductDto> findAll(Pageable pageable) {
        log.info("Products successfully selected");
        return productRepository.findAll(pageable).map(productMapper::toDto);
    }

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        log.info("Product successfully created");
        return productMapper.toDto(productRepository.save(productMapper.toEntity(productDto)));
    }

    @Override
    public ProductDto findById(Long id) {
        log.info("Product with id " + id + " successfully selected");
        return productRepository.findById(id)
                .map(productMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found by id: " + id));
    }
}
