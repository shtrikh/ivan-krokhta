package org.example.internetshopspring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.internetshopspring.dto.ProductDto;
import org.example.internetshopspring.service.ProductService;
import org.example.internetshopspring.util.TestUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.example.internetshopspring.util.TestUtil.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {ProductController.class})
class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private ProductService productService;

    @Test
    void findByIdSuccess() throws Exception {
        ProductDto productDto = createProductDto();

        when(productService.findById(PRODUCT_ID)).thenReturn(productDto);

        mockMvc.perform(get("/products/" + PRODUCT_ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(PRODUCT_ID));
    }

    @Test
    void createProduct() throws Exception {
        ProductDto productDtoWithoutId = TestUtil.createProductDtoWithoutId();
        ProductDto productDto = TestUtil.createProductDto();
        when(productService.createProduct(any())).thenReturn(productDto);

        mockMvc.perform(post("/products")
                        .content(mapper.writeValueAsString(productDtoWithoutId))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(PRODUCT_ID));
    }

    @Test
    void findAll() throws Exception {
        ProductDto productDto1 = TestUtil.createProductDto();
        ProductDto productDto2 = TestUtil.createProductDto(2L);
        ProductDto productDto3 = TestUtil.createProductDto(3L);

        List<ProductDto> products = Arrays.asList(productDto1, productDto2, productDto3);

        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.DEFAULT_DIRECTION, "id"));

        when(productService.findAll(pageable)).thenReturn(new PageImpl<>(products));

        mockMvc.perform(get("/products?pageSize=10&pageNumber=0&sortValue=id"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements").value(3))
                .andExpect(jsonPath("$.content[0].id").value(PRODUCT_ID))
                .andExpect(jsonPath("$.content[1].id").value(2L))
                .andExpect(jsonPath("$.content[2].id").value(3L));

        verify(productService, times(1)).findAll(pageable);
    }
}
