package com.trackingsystem.user.controller;

import com.trackingsystem.user.dto.UserDTO;
import com.trackingsystem.user.model.User;
import com.trackingsystem.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<User> createUser(@Valid @RequestBody UserDTO userDTO){
        return ResponseEntity.ok().body(userService.createUser(userDTO));
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id){
        return ResponseEntity.ok().body(userService.getUser(id));
    }
    @GetMapping("/check/{id}")
    public ResponseEntity<HttpStatus> checkUserResponse(@PathVariable Long id){
        return ResponseEntity.ok().body(userService.checkUserResponse(id));
    }
    @GetMapping("/email/{id}")
    public ResponseEntity<String> getEmailUserResponse(@PathVariable Long id){
        return ResponseEntity.ok().body(userService.getUserEmailResponse(id));
    }
    @GetMapping("/sms/{id}")
    public ResponseEntity<String> getPhoneNumberUserResponse(@PathVariable Long id){
        return ResponseEntity.ok().body(userService.getUserPhoneNumberResponse(id));
    }
    @GetMapping("")
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id , @RequestBody UserDTO userDTO){
        return ResponseEntity.ok().body(userService.updateUser(id,userDTO));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }
}
