package com.example.MiniProjectApis.Dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

    private Long id;

    @NotNull(message = "UserName can't be null or empty")
    @NotEmpty(message = "UserName can't be null or empty")
    private String name;

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
