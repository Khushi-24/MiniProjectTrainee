package com.example.MiniProjectApis.Service;

import com.example.MiniProjectApis.Dtos.AddActivityDto;
import com.example.MiniProjectApis.Dtos.ListOfActivityDto;
import com.example.MiniProjectApis.Dtos.UpdateActivityDto;
import com.example.MiniProjectApis.Dtos.ViewActivityDto;

import java.util.List;

public interface ActivityService {
    AddActivityDto addActivity(AddActivityDto addActivityDto);

    ViewActivityDto viewActivity(Long activityId);

    void updateActivity(UpdateActivityDto updateActivityDto, Long activityId);

    void deleteActivity(Long activityId);

    Long getCountOfActivities();

    List<ListOfActivityDto> getListOfActivities();

    List<ListOfActivityDto> getListOfActivityByProperty(Long propertyId);
}
