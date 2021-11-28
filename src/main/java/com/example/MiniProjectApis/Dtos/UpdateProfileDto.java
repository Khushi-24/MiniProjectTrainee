package com.example.MiniProjectApis.Dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateProfileDto {

    @NotNull(message = "user Id can't be null or empty.")
    private Long id;

    private String name;

    @NotEmpty(message = "email can't be null or empty.")
    @NotNull(message = "email can't be null or empty.")
    @Email(message = "please enter valid email format.")
    private String email;

    @NotNull(message = "country code can't be null or empty")
    @NotEmpty(message = "country code can't be null or empty")
    private String countryCode;

    @NotNull(message = "phone number be null or empty")
    @NotEmpty(message = "phone number be null or empty")
    private String phoneNumber;

    private String profilePicture;

}
