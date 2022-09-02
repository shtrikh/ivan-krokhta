package org.example.internetshopspring.service;

import org.example.internetshopspring.dto.ProductDto;
import org.example.internetshopspring.entity.Product;
import org.example.internetshopspring.exception.EntityNotFoundException;
import org.example.internetshopspring.mapper.ProductMapper;
import org.example.internetshopspring.repository.ProductRepository;
import org.example.internetshopspring.service.impl.ProductServiceImpl;
import org.example.internetshopspring.util.TestUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.example.internetshopspring.util.TestUtil.PRODUCT_ID;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {
    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;
    @Mock
    private ProductMapper productMapper;

    @Test
    void findByIdSuccess() {
        Product product = TestUtil.createProduct();
        ProductDto productDto = TestUtil.createProductDto();

        when(productRepository.findById(PRODUCT_ID)).thenReturn(Optional.of(product));
        when(productMapper.toDto(product)).thenReturn(productDto);

        ProductDto result = productService.findById(PRODUCT_ID);

        assertEquals(PRODUCT_ID, result.getId());
    }

    @Test
    void findByIdNotFound() {
        when(productRepository.findById(PRODUCT_ID)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> productService.findById(PRODUCT_ID));
    }

    @Test
    void createUser() {
        Product testProduct = TestUtil.createProduct();
        ProductDto testProductDto = TestUtil.createProductDto();
        when(productMapper.toDto(testProduct)).thenReturn(testProductDto);
        when(productRepository.save(ArgumentMatchers.any())).thenReturn(testProduct);

        ProductDto productDto = productService.createProduct(testProductDto);

        assertThat(productDto, allOf(
                hasProperty("name", equalTo(testProduct.getName())),
                hasProperty("category", equalTo(testProduct.getCategory())),
                hasProperty("price", equalTo(testProduct.getPrice()))
        ));
    }

    @Test
    void findAll() {
        ProductDto productDto1 = TestUtil.createProductDto();
        ProductDto productDto2 = TestUtil.createProductDto(2L);
        ProductDto productDto3 = TestUtil.createProductDto(3L);

        Product product1 = TestUtil.createProduct();
        Product product2 = TestUtil.createProduct(2L);
        Product product3 = TestUtil.createProduct(3L);

        when(productMapper.toDto(product1)).thenReturn(productDto1);
        when(productMapper.toDto(product2)).thenReturn(productDto2);
        when(productMapper.toDto(product3)).thenReturn(productDto3);


        List<Product> users = Arrays.asList(product1, product2, product3);

        Pageable pageable = Pageable.unpaged();

        when(productRepository.findAll(pageable))
                .thenReturn(new PageImpl<>(users, pageable, users.size()));

        Page<ProductDto> resultPageableUsers = productService.findAll(pageable);

        assertThat(resultPageableUsers.getContent(), hasItems(productDto1, productDto2, productDto3));

        verify(productRepository, times(1)).findAll(pageable);
    }

    @Test
    void findAllEmptyResult() {
        when(productRepository.findAll(ArgumentMatchers.any(Pageable.class))).thenReturn(new PageImpl<>(Collections.emptyList()));
        Page<ProductDto> resultPageableUsers = productService.findAll(Pageable.unpaged());
        assertTrue(resultPageableUsers.isEmpty());
    }
}
