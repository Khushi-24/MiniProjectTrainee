package com.example.MiniProjectApis.Dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AddActivityImageDto {

    @NotNull(message = "Image name can't be null")
    @NotEmpty(message = "Image name can't be empty")
    private String imageName;

    @NotNull(message = "Activity Id can't be null")
    private Long activityId;
}
