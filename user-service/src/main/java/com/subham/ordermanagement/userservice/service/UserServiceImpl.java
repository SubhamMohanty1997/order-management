package com.subham.ordermanagement.user_service.service;

import com.subham.ordermanagement.user_service.dto.UserDto;
import com.subham.ordermanagement.user_service.entity.User;
import com.subham.ordermanagement.user_service.exception.ResourceNotFoundException;
import com.subham.ordermanagement.user_service.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class UserServiceImpl implements UserService{

    @Autowired
     private UserRepository userRepository;

    @Override
    public UserDto createUser(User user) {
        User newUser = new User();
        newUser.setName(user.getName());
        newUser.setEmail(user.getEmail());
        User savedUser = userRepository.save(newUser);
        log.info("User created successfully with userId={}", savedUser.getId());
        return new UserDto(savedUser.getId().toHexString(),
                savedUser.getName(),savedUser.getEmail());
    }

    @Override
    public UserDto getUserById(String id) {
        log.info("Fetching user by id={}", id);
        User user = userRepository.findById(new ObjectId(id))
                .orElseThrow(()->new ResourceNotFoundException("User not found with ID: "+id));
        return new UserDto(user.getId().toHexString(),
                user.getName(), user.getEmail());
    }

    @Override
    public void deleteUserById(String id) {
        log.info("Deleting user with id={}", id);
        User user = userRepository.findById(new ObjectId(id))
                .orElseThrow(()->new ResourceNotFoundException("User not found with ID: "+id));
        userRepository.deleteById(new ObjectId(id));
        log.info("User deleted successfully, id={}", id);
    }
}
