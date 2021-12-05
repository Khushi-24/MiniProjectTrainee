package com.example.MiniProjectApis.Dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
public class JwtRequest {

    @NotEmpty(message = "email can't be null or empty.")
    @NotNull(message = "email can't be null or empty.")
    private String email;

    @NotNull(message = "user password can't be null or empty")
    @NotEmpty(message = "user password can't be null or empty")
    private String password;
}
