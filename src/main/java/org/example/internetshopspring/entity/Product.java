package org.example.internetshopspring.entity;

import lombok.*;
import org.example.internetshopspring.enums.Category;
import org.example.internetshopspring.enums.Size;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "product")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "color")
    private String color;

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(name = "size")
    @Enumerated(EnumType.STRING)
    private Size size;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "price_uah")
    private BigDecimal priceUah;

    @Column(name = "added_time", updatable = false)
    @CreationTimestamp
    private LocalDateTime addedTime;
}
