package com.trackingsystem.user.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
public class UserDTO {

    @NotEmpty
    private String username;
    private Integer password;
    @NotBlank
    private String mail;
    private String phoneNumber;
}
