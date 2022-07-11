package org.example.internetshopspring.dto;

import lombok.*;
import org.example.internetshopspring.enums.Status;

@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    long id;
    long userId;
    long productId;
    Status status;
}
