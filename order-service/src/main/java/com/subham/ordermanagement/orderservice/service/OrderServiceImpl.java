package com.subham.ordermanagement.orderservice.service;

import com.subham.ordermanagement.orderservice.dto.OrderResponseDto;
import com.subham.ordermanagement.orderservice.dto.UserDto;
import com.subham.ordermanagement.orderservice.entity.Order;
import com.subham.ordermanagement.orderservice.exception.OrderNotFoundException;
import com.subham.ordermanagement.orderservice.repository.OrderRepository;
import com.subham.ordermanagement.orderservice.util.UserServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserServiceClient userServiceClient;

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
    public List<OrderResponseDto> getOrdersByUserId(String userId) {
        UserDto userDto = userServiceClient.getUserById(userId);
        System.out.println(userDto.getId()+", "+userDto.getName()+", "+userDto.getEmail());

        List<Order> orders = orderRepository.findByUserId(userId);

        List<OrderResponseDto> orderResponse = orders.stream()
                .map(order-> new OrderResponseDto(order.getId(),order.getProductName(),
                        order.getQuantity(),order.getPrice(),order.getStatus(),
                        userDto.getId(),userDto.getName(),userDto.getEmail()
                )).toList();

        return orderResponse;

    }
}
