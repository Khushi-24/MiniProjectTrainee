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
public class LandOwnerDto {

    @NotNull(message = "LandOwner name can't be null or empty")
    @NotEmpty(message = "LandOwner name can't be null or empty")
    private String name;

    @NotEmpty(message = "LandOwner email can't be null or empty.")
    @NotNull(message = "LandOwner email can't be null or empty.")
    @Email(message = "please enter valid email format.")
    private String email;

    @NotNull(message = "LandOwner password can't be null or empty")
    @NotEmpty(message = "LandOwner password can't be null or empty")
    @Size(min= 8, max= 16, message = "password size can't be less than 8 character and more than 16 characters")
    private String password;

}
