package com.trackingsystem.user.service.impl;

import com.trackingsystem.user.dto.UserDTO;
import com.trackingsystem.user.exception.UserConditionManager;
import com.trackingsystem.user.exception.UserNotFoundException;
import com.trackingsystem.user.model.User;
import com.trackingsystem.user.repository.UserRepository;
import com.trackingsystem.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;


    @Override
    public User createUser(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);

        UserConditionManager.checkUsernameCondition(user.getUsername(),user.getPassword());
        UserConditionManager.checkEmailCondition(user.getMail());
        UserConditionManager.checkPhoneNumber(user.getPhoneNumber());

        userRepository.save(user);
        return user;
    }

    @Override
    public User getUser(Long id) {
        // we will update in orElseThrow after add to exception and validation handle.
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found by id to get operation!!!"));

    }

    @Override
    public User updateUser(Long id,UserDTO userDTO) {

        log.info("id info for update operation:"+id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found by id to update operation!!!"));

        // you will use map struct.
        user.setMail(userDTO.getMail());
        user.setPassword((userDTO.getPassword()));
        user.setPhoneNumber(userDTO.getPhoneNumber());

        log.info("User Data to update User:{}",user);

        userRepository.save(user);
        return user;
    }

    @Override
    public void deleteUser(Long id) {

        userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found by id to delete operation!!!"));

        userRepository.deleteById(id);
    }

    @Override
    public HttpStatus checkUserResponse(Long id) {
        Optional<User> user = userRepository.findById(id);
        log.info("check user:"+user);
        if(user.equals(Optional.empty()))
            return HttpStatus.NOT_FOUND;
        return HttpStatus.OK;
    }

    @Override
    public String getUserEmailResponse(Long id) {
        User user = userRepository.findById(id).
                orElseThrow(()-> new UserNotFoundException("User not found for get message!!!"));
        return user.getMail();
    }
}
