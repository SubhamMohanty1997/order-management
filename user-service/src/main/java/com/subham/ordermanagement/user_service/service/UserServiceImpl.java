package com.subham.ordermanagement.user_service.service;

import com.subham.ordermanagement.user_service.dto.UserDto;
import com.subham.ordermanagement.user_service.entity.User;
import com.subham.ordermanagement.user_service.exception.ResourceNotFoundException;
import com.subham.ordermanagement.user_service.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return new UserDto(savedUser.getId().toHexString(),
                savedUser.getName(),savedUser.getEmail());
    }

    @Override
    public UserDto getUserById(String id) {
        User user = userRepository.findById(new ObjectId(id))
                .orElseThrow(()->new ResourceNotFoundException("User not found with ID: "+id));
        return new UserDto(user.getId().toHexString(),
                user.getName(), user.getEmail());
    }

    @Override
    public void deleteUserById(String id) {
        User user = userRepository.findById(new ObjectId(id))
                .orElseThrow(()->new ResourceNotFoundException("User not found with ID: "+id));
        userRepository.deleteById(new ObjectId(id));
    }
}
