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

    private String profilePicture;

}
