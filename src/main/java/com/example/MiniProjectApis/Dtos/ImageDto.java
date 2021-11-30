package com.example.MiniProjectApis.Dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Getter
@Setter
public class ImageDto {

    @NotNull(message = "Image name can't be null or empty")
    @NotEmpty(message = "Image name can't be null or empty")
    private String imageName;

}
