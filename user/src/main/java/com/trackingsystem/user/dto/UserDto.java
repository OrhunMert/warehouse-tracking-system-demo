package com.trackingsystem.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
@Data
public class UserDto {
    @NotEmpty
    private String username;
    private Integer password;
    @NotBlank
    private String mail;
    @JsonProperty("phone_number")
    private String phoneNumber;
}
