package com.example.MiniProjectApis.Dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AddAmenityToActivity {

    @NotNull(message = "activity Id can't be null")
    private Long activityId;

    @NotNull(message = "amenity Id can't be null")
    private Long amenityId;
}
