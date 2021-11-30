package com.example.MiniProjectApis.Dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ViewActivityDto {

    private Long activityId;
    private String activityName;
    private String shortDescription;
    private String aboutActivity;
    private Long propertyId;
    List<ViewImageResponseDto> imageDtoList;

}
