package com.example.MiniProjectApis.Dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticateResponse {

    private String token;

    public AuthenticateResponse(String token) {
        this.token = token;
    }

    public AuthenticateResponse() {
    }
}
