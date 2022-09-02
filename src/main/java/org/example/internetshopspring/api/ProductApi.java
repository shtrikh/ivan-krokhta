package org.example.internetshopspring.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.internetshopspring.dto.ProductDto;
import org.example.internetshopspring.dto.group.OnCreate;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Internet Shop")
@RequestMapping("/products")
public interface ProductApi {

    @Operation(summary = "Find all")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    Page<ProductDto> findAll(@RequestParam("pageSize") int pageSize, @RequestParam("pageNumber") int pageNumber,
                             @RequestParam("sortValue") String sortValue);

    @Operation(summary = "Create user")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    ProductDto createProduct(@RequestBody @Validated(OnCreate.class) ProductDto productDto);

    @Operation(summary = "Find by id")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    ProductDto findById(@PathVariable("id") Long id);
}
