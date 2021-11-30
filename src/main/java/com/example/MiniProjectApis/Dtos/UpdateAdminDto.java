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
public class UpdateAdminDto {

    @NotNull(message = "Admin Id can't be null or empty.")
    private Long id;

    @NotNull(message = "Admin name can't be null or empty")
    @NotEmpty(message = "Admin name can't be null or empty")
    private String name;

    @NotEmpty(message = "email can't be null or empty.")
    @NotNull(message = "email can't be null or empty.")
    @Email(message = "please enter valid email format.")
    private String email;

    private String password;

}
