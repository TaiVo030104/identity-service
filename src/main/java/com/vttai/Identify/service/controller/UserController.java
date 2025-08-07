package com.vttai.Identify.service.controller;

import com.vttai.Identify.service.dto.request.UserCreateRequest;
import com.vttai.Identify.service.dto.request.UserUpdateRequest;
import com.vttai.Identify.service.dto.response.UserResponse;
import com.vttai.Identify.service.entity.User;
import com.vttai.Identify.service.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    User createUser(@RequestBody @Valid UserCreateRequest request){
        return userService.createUserRequest(request);
    }

    @GetMapping
    List<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/{userId}")
        UserResponse getUser(@PathVariable("userId") String userId) { return userService.getUser(userId); }


    @PutMapping("/{userId}")
    UserResponse updateUser(@PathVariable String userId, @RequestBody UserUpdateRequest request){
        return userService.updateUser(userId, request);
    }
    @DeleteMapping("/{userId}")
    String deleteuser(@PathVariable String userId){
        userService.deleteUser(userId);
        return "User has been deleted";
    }



























}
