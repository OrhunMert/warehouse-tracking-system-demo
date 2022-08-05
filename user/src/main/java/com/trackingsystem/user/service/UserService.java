package com.trackingsystem.user.service;

import com.trackingsystem.user.dto.GetUserToNotificationDto;
import com.trackingsystem.user.dto.UserDto;
import com.trackingsystem.user.model.User;
import org.springframework.http.HttpStatus;

public interface UserService {
    User createUser(UserDto userDTO);
    User getUser(Long id);
    User updateUser(Long id, UserDto userDTO);
    void deleteUser(Long id);
    HttpStatus checkUserResponse(Long id);
    GetUserToNotificationDto getUserToNotification(Long id);

}
