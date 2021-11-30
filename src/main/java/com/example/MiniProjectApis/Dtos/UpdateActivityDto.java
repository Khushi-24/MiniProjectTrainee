package com.example.MiniProjectApis.Dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UpdateActivityDto {

    private Long activityId;

    @NotNull(message = "Property name can't be null or empty")
    @NotEmpty(message = "Property name can't be null or empty")
    private String activityName;

    @NotNull(message = "Property name can't be null or empty")
    @NotEmpty(message = "Property name can't be null or empty")
    private String shortDescription;

    @NotNull(message = "Property name can't be null or empty")
    @NotEmpty(message = "Property name can't be null or empty")
    private String aboutActivity;

    @NotNull(message = "Property name can't be null")
    private Long propertyId;
}
