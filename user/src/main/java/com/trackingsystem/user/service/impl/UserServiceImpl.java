package com.trackingsystem.user.service.impl;

import com.trackingsystem.user.dto.NotificationDto;
import com.trackingsystem.user.dto.UserDto;

import com.trackingsystem.user.exception.RegexNotValidException;
import com.trackingsystem.user.exception.UserConditionManager;
import com.trackingsystem.user.exception.UserNotFoundException;

import com.trackingsystem.user.model.User;
import com.trackingsystem.user.repository.UserRepository;
import com.trackingsystem.user.service.UserService;
import com.trackingsystem.user.validator.RegexParametersValidation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    @Override
    public UserDto createUser(UserDto userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        UserConditionManager.checkUsernameCondition(user.getUsername(),user.getPassword());
        if(!(RegexParametersValidation.checkEmailValid(
                user.getMail())))
            throw new RegexNotValidException("Email is not valid for send the email to user!!!");
        else if(!(RegexParametersValidation.checkPhoneValid(
                user.getPhoneNumber())))
            throw new RegexNotValidException("Phone Number is not valid for send the sms to user!!!");
        userRepository.save(user);
        return userDTO;
    }
    @Override
    public UserDto getUser(Long id) {
        // we will update in orElseThrow after add to exception and validation handle.
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found by id to get operation!!!"));
        return modelMapper.map(user, UserDto.class);
    }
    @Override
    public UserDto updateUser(Long id, UserDto userDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found by id to update operation!!!"));
        UserConditionManager.checkUsernameCondition(userDTO.getUsername(),userDTO.getPassword());
        if(!(RegexParametersValidation.checkEmailValid(
                userDTO.getMail())))
            throw new RegexNotValidException("Email is not valid for send the email to user!!!");
        else if(!(RegexParametersValidation.checkPhoneValid(
                userDTO.getPhoneNumber())))
            throw new RegexNotValidException("Phone Number is not valid for send the sms to user!!!");
        user.setMail(userDTO.getMail());
        user.setPassword((userDTO.getPassword()));
        user.setPhoneNumber(userDTO.getPhoneNumber());
        userRepository.save(user);
        return modelMapper.map(user, UserDto.class);
    }
    @Override
    public void deleteUser(Long id) {
        userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found by id to delete operation!!!"));
        userRepository.deleteById(id);
    }
    @Override
    public NotificationDto getUserToNotification(Long id) {
        User user = userRepository.findById(id).
                orElseThrow(()-> new UserNotFoundException("User not found for notification service!!!"));
        NotificationDto getUserToNotificationDto = modelMapper.map(user, NotificationDto.class);
        log.info("Get user information to notification:{}",getUserToNotificationDto);
        return getUserToNotificationDto;
    }
}
