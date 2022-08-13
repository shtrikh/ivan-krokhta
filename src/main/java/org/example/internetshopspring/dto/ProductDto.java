package org.example.internetshopspring.dto;

import lombok.*;
import org.example.internetshopspring.dto.group.OnCreate;
import org.example.internetshopspring.dto.group.OnUpdate;
import org.example.internetshopspring.enums.Category;
import org.example.internetshopspring.enums.Size;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    @Null(groups = OnCreate.class)
    @NotNull(groups = OnUpdate.class)
    private Long id;

    @NotBlank(groups = OnCreate.class)
    private String name;

    @NotBlank(groups = OnCreate.class)
    private String color;

    private Category category;

    private Size size;

    @Positive(groups = OnCreate.class)
    @NotNull(groups = OnCreate.class)
    private BigDecimal price;

    @Positive(groups = OnCreate.class)
    @NotNull(groups = OnCreate.class)
    private BigDecimal priceUah;

    @PastOrPresent(groups = OnCreate.class)
    private LocalDateTime addedTime;
}
