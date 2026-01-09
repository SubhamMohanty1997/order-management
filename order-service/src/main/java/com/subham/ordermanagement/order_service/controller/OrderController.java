package com.subham.ordermanagement.order_service.controller;

import com.subham.ordermanagement.order_service.dto.OrderResponseDto;
import com.subham.ordermanagement.order_service.entity.Order;
import com.subham.ordermanagement.order_service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/createOrder")
    public ResponseEntity<Order> createOrder(@RequestBody Order order){
        Order savedOrder = orderService.createOrder(order);
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }

    @GetMapping("/getOrder/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable("id") Long id){
        Order order = orderService.getOrderById(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @DeleteMapping("/deleteOrder/{id}")
    public ResponseEntity<String> deleteOrderById(@PathVariable("id") Long id){
        orderService.deleteOrderById(id);
        return new ResponseEntity<>("Order deleted successfully with ID: "+id, HttpStatus.OK);
    }

    @GetMapping("/getAllOrders")
    public ResponseEntity<List<Order>> getAllOrders(){
        List<Order> orders = orderService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/getOrdersByUserId/{id}")
    public ResponseEntity<List<OrderResponseDto>> getOrdersByUserId(@PathVariable("id") String userId){
        List<OrderResponseDto> orders = orderService.getOrdersByUserId(userId);
        return new ResponseEntity<>(orders,HttpStatus.OK);
    }


}
