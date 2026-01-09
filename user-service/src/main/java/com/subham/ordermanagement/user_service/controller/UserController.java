package com.subham.ordermanagement.user_service.controller;

import com.subham.ordermanagement.user_service.dto.UserDto;
import com.subham.ordermanagement.user_service.entity.User;
import com.subham.ordermanagement.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/createUser")
    public ResponseEntity<UserDto> createUser(@RequestBody User user){
        UserDto savedUser = userService.createUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") String id){
        UserDto user = userService.getUserById(id);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable("id") String id){
        userService.deleteUserById(id);
        return new ResponseEntity<>("User deleted successfully wit ID: "+id,HttpStatus.OK);
    }
}
