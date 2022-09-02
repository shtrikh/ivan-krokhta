package org.example.internetshopspring.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.example.internetshopspring.dto.OrderDto;
import org.example.internetshopspring.dto.ProductDto;
import org.example.internetshopspring.dto.UserDto;
import org.example.internetshopspring.entity.Order;
import org.example.internetshopspring.entity.Product;
import org.example.internetshopspring.entity.User;
import org.example.internetshopspring.enums.Category;
import org.example.internetshopspring.enums.Role;
import org.example.internetshopspring.enums.Size;
import org.example.internetshopspring.enums.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestUtil {

    public static final Long USER_ID = Long.valueOf(1);
    public static final String USER_LOGIN = "login";
    public static final String USER_PASSWORD = "password1234";
    public static final String USER_NAME = "Name";
    public static final String USER_SURNAME = "Surname";
    public static final String USER_EMAIL = "email@gmail.com";
    public static final Role USER_ROLE = Role.USER;

    public static final Long PRODUCT_ID = Long.valueOf(1);
    public static final String PRODUCT_NAME = "Name";
    public static final String PRODUCT_COLOR = "color";
    public static final Category PRODUCT_CATEGORY = Category.HOODIE;
    public static final Size PRODUCT_SIZE = Size.S;
    public static final BigDecimal PRODUCT_PRICE = BigDecimal.valueOf(10);
    public static final BigDecimal PRODUCT_PRICE_UAH = BigDecimal.valueOf(400);
    public static final LocalDateTime PRODUCT_ADDED_TIME = LocalDateTime
            .of(2022, 07, 27, 15,00,00);

    public static final Long ORDER_ID = Long.valueOf(1);
    public static final Status ORDER_STATUS = Status.BASKET;

    public static User createUser(){
        return createUser(USER_ID);
    }

    public static User createUser(Long id){
        return User.builder()
                .id(id)
                .login(USER_LOGIN)
                .password(USER_PASSWORD)
                .name(USER_NAME)
                .surname(USER_SURNAME)
                .email(USER_EMAIL)
                .role(USER_ROLE)
                .build();
    }

    public static UserDto createUserDto(){
        return createUserDto(USER_ID);
    }

    public static UserDto createUserDto(Long id){
        return UserDto.builder()
                .id(id)
                .login(USER_LOGIN)
                .password(USER_PASSWORD)
                .name(USER_NAME)
                .surname(USER_SURNAME)
                .email(USER_EMAIL)
                .role(USER_ROLE)
                .build();
    }

    public static UserDto createUserDtoWithoutId(){
        return UserDto.builder()
                .login(USER_LOGIN)
                .password(USER_PASSWORD)
                .name(USER_NAME)
                .surname(USER_SURNAME)
                .email(USER_EMAIL)
                .role(USER_ROLE)
                .build();
    }

    public static Product createProduct(Long id){
        return Product.builder()
                .id(id)
                .name(PRODUCT_NAME)
                .color(PRODUCT_COLOR)
                .category(PRODUCT_CATEGORY)
                .size(PRODUCT_SIZE)
                .price(PRODUCT_PRICE)
                .priceUah(PRODUCT_PRICE_UAH)
                .addedTime(PRODUCT_ADDED_TIME)
                .build();
    }

    public static Product createProduct(){
        return createProduct(PRODUCT_ID);
    }

    public static ProductDto createProductDto(Long id){
        return ProductDto.builder()
                .id(id)
                .name(PRODUCT_NAME)
                .color(PRODUCT_COLOR)
                .category(PRODUCT_CATEGORY)
                .size(PRODUCT_SIZE)
                .price(PRODUCT_PRICE)
                .priceUah(PRODUCT_PRICE_UAH)
                .addedTime(PRODUCT_ADDED_TIME)
                .build();
    }

    public static ProductDto createProductDto(){
        return createProductDto(PRODUCT_ID);
    }

    public static ProductDto createProductDtoWithoutId(){
        return ProductDto.builder()
                .name(PRODUCT_NAME)
                .color(PRODUCT_COLOR)
                .category(PRODUCT_CATEGORY)
                .size(PRODUCT_SIZE)
                .price(PRODUCT_PRICE)
                .priceUah(PRODUCT_PRICE_UAH)
                .addedTime(PRODUCT_ADDED_TIME)
                .build();
    }

    public static Order createOrder(Long id){
        return Order.builder()
                .id(id)
                .user(createUser())
                .product(createProduct())
                .status(ORDER_STATUS)
                .build();
    }

    public static Order createOrder(){
        return createOrder(ORDER_ID);
    }

    public static OrderDto createOrderDto(Long id){
        return OrderDto.builder()
                .id(id)
                .user(createUser())
                .product(createProduct())
                .status(ORDER_STATUS)
                .build();
    }

    public static OrderDto createOrderDto(){
        return createOrderDto(ORDER_ID);
    }

    public static OrderDto createOrderDtoWithoutId(){
        return OrderDto.builder()
                .user(createUser())
                .product(createProduct())
                .status(ORDER_STATUS)
                .build();
    }
}
