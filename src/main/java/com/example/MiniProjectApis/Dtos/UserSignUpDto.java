package com.example.MiniProjectApis.Dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserSignUpDto {

    @NotEmpty(message = "email can't be null or empty.")
    @NotNull(message = "email can't be null or empty.")
    @Email(message = "please enter valid email format.")
    private String email;

    @NotNull(message = "user password can't be null or empty")
    @NotEmpty(message = "user password can't be null or empty")
    @Size(min= 8, max= 16, message = "password size can't be less than 8 character and more than 16 characters")
    private String password;

    @NotNull(message = "country code can't be null or empty")
    @NotEmpty(message = "country code can't be null or empty")
    private String countryCode;

    @NotNull(message = "phone number be null or empty")
    @NotEmpty(message = "phone number be null or empty")
    private String phoneNumber;

    private String profilePicture;
}
