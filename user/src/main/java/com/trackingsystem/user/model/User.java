package com.trackingsystem.user.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "user",schema = "trackingsystemuser")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    private String username;
    private Integer password;
    private String mail;
    private String phoneNumber;

}
