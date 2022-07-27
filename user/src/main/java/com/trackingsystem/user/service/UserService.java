package com.trackingsystem.user.service;

import com.trackingsystem.user.dto.UserDTO;
import com.trackingsystem.user.model.User;


public interface UserService {

    User createUser(UserDTO userDTO);
    User getUser(Long id);
    User updateUser(Long id,UserDTO userDTO);
    void deleteUser(Long id);


}
