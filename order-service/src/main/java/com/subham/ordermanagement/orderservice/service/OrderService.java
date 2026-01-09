package com.subham.ordermanagement.orderservice.service;

import com.subham.ordermanagement.orderservice.dto.OrderResponseDto;
import com.subham.ordermanagement.orderservice.entity.Order;

import java.util.List;

public interface OrderService {
    Order createOrder(Order order);
    Order getOrderById(Long id);
    void deleteOrderById(Long id);
    List<Order> getAllOrders();
    List<OrderResponseDto> getOrdersByUserId(String userId);

}
