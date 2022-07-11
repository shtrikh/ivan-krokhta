package org.example.internetshopspring.entity;

import lombok.*;
import org.example.internetshopspring.enums.Status;

import javax.persistence.*;

@Entity
@Table(name = "order")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "status")
    private Status status;
}
