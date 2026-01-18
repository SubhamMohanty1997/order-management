package com.subham.ordermanagement.orderservice.service;

import com.subham.ordermanagement.orderservice.dto.OrderResponseDto;
import com.subham.ordermanagement.orderservice.dto.UserDto;
import com.subham.ordermanagement.orderservice.entity.Order;
import com.subham.ordermanagement.orderservice.exception.OrderNotFoundException;
import com.subham.ordermanagement.orderservice.repository.OrderRepository;
import com.subham.ordermanagement.orderservice.util.UserServiceClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserServiceClient userServiceClient;

    @Override
    public Order createOrder(Order order) {
        Order savedOrder = orderRepository.save(order);
        log.info("Order created successfully with orderId={}",savedOrder.getId());
        return savedOrder;
    }

    @Override
    public Order getOrderById(Long id) {
        log.info("Fetching order by id={}",id);
        return orderRepository.findById(id)
                .orElseThrow(()->new OrderNotFoundException("Order not found with id: "+id));
    }

    @Override
    public void deleteOrderById(Long id) {
        log.info("Deleting order with id={}", id);
        Order order = orderRepository.findById(id)
                        .orElseThrow(()->new OrderNotFoundException("Order not found with ID: "+id));
        orderRepository.deleteById(id);
        log.info("Order deleted successfully, id={}", id);
    }

    @Override
    public List<Order> getAllOrders() {
        log.info("Fetching all orders");
        List<Order> orders = orderRepository.findAll();
        log.debug("Total orders found={}",orders.size());
        return orders;
    }

    @Override
    public List<OrderResponseDto> getOrdersByUserId(String userId) {
        log.info("Fetching orders for userId={}", userId);

        UserDto userDto = userServiceClient.getUserById(userId);
        log.debug("User fetched: id={}, name={}, email={}",
                userDto.getId(), userDto.getName(), userDto.getEmail());

        List<Order> orders = orderRepository.findByUserId(userId);
        log.info("Found {} orders for userId={}", orders.size(), userId);

        List<OrderResponseDto> orderResponse = orders.stream()
                .map(order-> new OrderResponseDto(order.getId(),order.getProductName(),
                        order.getQuantity(),order.getPrice(),order.getStatus(),
                        userDto.getId(),userDto.getName(),userDto.getEmail()
                )).toList();

        log.debug("OrderResponseDto prepared for userId={}", userId);

        return orderResponse;

    }
}
