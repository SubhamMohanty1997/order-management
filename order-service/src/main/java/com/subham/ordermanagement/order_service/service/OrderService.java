package com.subham.ordermanagement.order_service.service;

import com.subham.ordermanagement.order_service.entity.Order;

import java.util.List;

public interface OrderService {
    Order createOrder(Order order);
    Order getOrderById(Long id);
    void deleteOrderById(Long id);
    List<Order> getAllOrders();

    List<Order> getOrdersByUserId(String userId);

}
