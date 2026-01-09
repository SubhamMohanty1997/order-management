package com.subham.ordermanagement.order_service.util;

import com.subham.ordermanagement.order_service.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UserServiceClient {

    @Autowired
    private RestTemplate restTemplate;


    private String userServiceUrl;

    public UserServiceClient(RestTemplate restTemplate, @Value("${app.user-service.url}") String userServiceUrl) {
        this.restTemplate = restTemplate;
        this.userServiceUrl=userServiceUrl;
    }

    public UserDto getUserById(String userId){
        System.out.println(userServiceUrl+"/"+userId);
        return restTemplate.getForObject(userServiceUrl+"/"+userId,UserDto.class);
    }
}
