package com.example.miniaiprojekt.security.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtRequestModel  {
    private String username;
    private String password;
}
