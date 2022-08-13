package org.example.internetshopspring.dto;

import lombok.*;
import org.example.internetshopspring.dto.group.OnCreate;
import org.example.internetshopspring.dto.group.OnUpdate;
import org.example.internetshopspring.entity.Product;
import org.example.internetshopspring.entity.User;
import org.example.internetshopspring.enums.Status;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    @Null(groups = OnCreate.class)
    @NotNull(groups = OnUpdate.class)
    private Long id;

    @NotNull(groups = OnCreate.class)
    private User user;

    @NotNull(groups = OnCreate.class)
    private Product product;

    private Status status;
}
