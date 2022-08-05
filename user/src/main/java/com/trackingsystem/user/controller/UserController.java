package com.trackingsystem.user.controller;

import com.trackingsystem.user.dto.GetUserToNotificationDto;
import com.trackingsystem.user.dto.UserDto;
import com.trackingsystem.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping()
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        return ResponseEntity.ok().body(userService.createUser(userDto));
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id){
        return ResponseEntity.ok().body(userService.getUser(id));
    }
    @GetMapping("/notification/{id}")
    public ResponseEntity<GetUserToNotificationDto> getUserToNotification(@PathVariable Long id){
        return ResponseEntity.ok().body(userService.getUserToNotification(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id , @RequestBody UserDto userDTO){
        return ResponseEntity.ok().body(userService.updateUser(id,userDTO));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
