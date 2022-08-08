package org.example.internetshopspring.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.internetshopspring.api.ProductApi;
import org.example.internetshopspring.dto.ProductDto;
import org.example.internetshopspring.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Log4j2
public class ProductController implements ProductApi {

    private final ProductService productService;

    @Override
    public Page<ProductDto> findAll(int pageSize, int pageNumber) {
        Page<ProductDto> products = productService.findAll(PageRequest.of(pageNumber, pageSize));
        log.info("Products successfully selected");
        return products;
    }

    @Override
    public ProductDto createProduct( ProductDto productDto) {
        ProductDto addedProduct = productService.createProduct(productDto);
        log.info("Product successfully created");
        return addedProduct;
    }

    @Override
    public ProductDto findById( Long id) {
        ProductDto product = productService.findById(id);
        log.info("Product with id " + id + " successfully selected");
        return product;
    }
}
