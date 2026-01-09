package com.subham.ordermanagement.orderservice.util;

import com.subham.ordermanagement.orderservice.dto.UserDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceClient {

    @Autowired
    private RestTemplate restTemplate;


    private String userServiceUrl;

    public UserServiceClient(RestTemplate restTemplate, @Value("${app.user-service.url}") String userServiceUrl) {
        this.restTemplate = restTemplate;
        this.userServiceUrl=userServiceUrl;
    }

    @CircuitBreaker(name = "userServiceCB", fallbackMethod = "fallbackGetUser")
    public UserDto getUserById(String userId){
        System.out.println(userServiceUrl+"/"+userId);
        return restTemplate.getForObject(userServiceUrl+"/"+userId,UserDto.class);
    }

    public UserDto fallbackGetUser(String userId, Throwable ex) {
        return new UserDto(
                userId,
                "UNKNOWN_USER",
                "unknown@email.com"
        );
    }
}
