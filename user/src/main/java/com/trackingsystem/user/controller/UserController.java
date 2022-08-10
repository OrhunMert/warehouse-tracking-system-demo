package com.trackingsystem.user.controller;

import com.trackingsystem.user.dto.NotificationDto;
import com.trackingsystem.user.dto.UserDto;
import com.trackingsystem.user.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import javax.validation.Valid;
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Api(value = "User's Api")
public class UserController {
    private final UserService userService;
    @PostMapping()
    @ApiOperation(value = "Create User")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        return ResponseEntity.ok().body(userService.createUser(userDto));
    }
    @GetMapping("/{id}")
    @ApiOperation(value = "Get User by id")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id){
        return ResponseEntity.ok().body(userService.getUser(id));
    }
    @GetMapping("/notification/{id}")
    @ApiOperation(value = "Get User Email and Phone number for nofitication")
    public ResponseEntity<NotificationDto> getUserToNotification(@PathVariable Long id){
        return ResponseEntity.ok().body(userService.getUserToNotification(id));
    }
    @PutMapping("/{id}")
    @ApiOperation(value = "Update User")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id , @RequestBody UserDto userDTO){
        return ResponseEntity.ok().body(userService.updateUser(id,userDTO));
    }
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete User")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
