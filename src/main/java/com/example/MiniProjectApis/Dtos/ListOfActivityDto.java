package com.example.MiniProjectApis.Dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListOfActivityDto {

    private Long activityId;
    private String activityName;
    private String shortDescription;
    private String aboutActivity;
    private Long propertyId;
    private String propertyName;

    public ListOfActivityDto(Long activityId, String activityName, String shortDescription, String aboutActivity, Long propertyId, String propertyName) {

        this.activityId = activityId;
        this.activityName = activityName;
        this.shortDescription = shortDescription;
        this.aboutActivity = aboutActivity;
        this.propertyId = propertyId;
        this.propertyName = propertyName;
    }
}
