package com.example.MiniProjectApis.Dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ActivityAmenitiesDto {

    @NotNull(message = "Amenity Id can't be null")
    private Long amenityId;

}
