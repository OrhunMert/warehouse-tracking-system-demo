package com.trackingsystem.user.service;

import com.trackingsystem.user.dto.GetUserToNotificationDto;
import com.trackingsystem.user.dto.UserDto;
public interface UserService {
    UserDto createUser(UserDto userDTO);
    UserDto getUser(Long id);
    UserDto updateUser(Long id, UserDto userDTO);
    void deleteUser(Long id);
    GetUserToNotificationDto getUserToNotification(Long id);

}
