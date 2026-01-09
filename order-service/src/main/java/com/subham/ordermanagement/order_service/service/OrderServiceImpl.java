package com.subham.ordermanagement.order_service.service;

import com.subham.ordermanagement.order_service.entity.Order;
import com.subham.ordermanagement.order_service.exception.OrderNotFoundException;
import com.subham.ordermanagement.order_service.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(()->new OrderNotFoundException("Order not found with id: "+id));
    }

    @Override
    public void deleteOrderById(Long id) {
        Order order = orderRepository.findById(id)
                        .orElseThrow(()->new OrderNotFoundException("Order not found with ID: "+id));
        orderRepository.deleteById(id);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> getOrdersByUserId(String userId) {
        return orderRepository.findByUserId(userId);
    }
}
