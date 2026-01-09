package com.subham.ordermanagement.order_service.dto;

import com.subham.ordermanagement.order_service.util.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDto {
    private Long id;
    private String productName;
    private Integer quantity;
    private BigDecimal price;
    private OrderStatus status;
    private String userId;
    private String userName;
    private String userEmail;
}
