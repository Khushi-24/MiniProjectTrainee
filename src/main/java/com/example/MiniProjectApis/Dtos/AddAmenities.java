package com.example.MiniProjectApis.Dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AddAmenities {

    @NotNull(message = "Amenity name can't be null or empty")
    @NotEmpty(message = "Amenity name can't be null or empty")
    private String name;

    @NotNull(message = "Amenity icon can't be null or empty")
    @NotEmpty(message = "Amenity icon can't be null or empty")
    private String icon;

}
