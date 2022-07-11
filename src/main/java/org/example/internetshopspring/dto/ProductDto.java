package org.example.internetshopspring.dto;

import lombok.*;
import org.example.internetshopspring.enums.Category;
import org.example.internetshopspring.enums.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
    private String color;
    private Category category;
    private Size size;
    private BigDecimal price;
    private BigDecimal priceUah;
    private LocalDateTime addedTime;
}
