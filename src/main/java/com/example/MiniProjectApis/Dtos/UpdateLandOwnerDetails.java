package com.example.MiniProjectApis.Dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateLandOwnerDetails {

    @NotNull(message = "LandOwner Id can't be null or empty.")
    private Long id;

    @NotNull(message = "LandOwner name can't be null or empty")
    @NotEmpty(message = "LandOwner name can't be null or empty")
    private String name;

    @NotEmpty(message = "LandOwner email can't be null or empty.")
    @NotNull(message = "LandOwner email can't be null or empty.")
    @Email(message = "please enter valid email format.")
    private String email;

    @NotNull(message = "country code can't be null or empty")
    @NotEmpty(message = "country code can't be null or empty")
    private String countryCode;

    @NotEmpty(message = "LandOwner phoneNo can't be null or empty.")
    @NotNull(message = "LandOwner phoneNo can't be null or empty.")
    private String phoneNumber;

    @NotEmpty(message = "LandOwner address can't be null or empty.")
    @NotNull(message = "LandOwner address can't be null or empty.")
    private String address;

    private String password;
}
