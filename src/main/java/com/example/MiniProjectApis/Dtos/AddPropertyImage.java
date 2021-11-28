package com.example.MiniProjectApis.Dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AddPropertyImage {

    @NotNull(message = "Image name can't be null")
    @NotEmpty(message = "Image name can't be empty")
    private String imageName;

    @NotNull(message = "Property Id can't be null")
    private Long propertyId;

}
