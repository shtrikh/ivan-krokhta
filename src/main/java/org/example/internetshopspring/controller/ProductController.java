package org.example.internetshopspring.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.internetshopspring.dto.ProductDto;
import org.example.internetshopspring.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Log4j2
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public Page<ProductDto> findAll(@RequestParam("pageSize") int pageSize, @RequestParam("pageNumber") int pageNumber) {
        Page<ProductDto> products = productService.findAll(PageRequest.of(pageNumber, pageSize));
        log.info("Products successfully selected");
        return products;
    }

    @PostMapping
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        ProductDto addedProduct = productService.createProduct(productDto);
        log.info("Product successfully created");
        return addedProduct;
    }

    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable("id") Long id) {
        ProductDto product = productService.findById(id);
        log.info("Product with id " + id + " successfully selected");
        return product;
    }
}
