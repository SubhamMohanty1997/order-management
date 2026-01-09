package com.subham.ordermanagement.user_service.service;

import com.subham.ordermanagement.user_service.dto.UserDto;
import com.subham.ordermanagement.user_service.entity.User;



public interface UserService {
    UserDto createUser(User user);
    UserDto getUserById(String id);
    void deleteUserById(String id);
}
