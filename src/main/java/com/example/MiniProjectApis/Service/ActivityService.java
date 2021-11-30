package com.example.MiniProjectApis.Service;

import com.example.MiniProjectApis.Dtos.AddActivityDto;
import com.example.MiniProjectApis.Dtos.UpdateActivityDto;
import com.example.MiniProjectApis.Dtos.ViewActivityDto;

public interface ActivityService {
    AddActivityDto addActivity(AddActivityDto addActivityDto);

    ViewActivityDto viewActivity(Long activityId);

    UpdateActivityDto updateActivity(UpdateActivityDto updateActivityDto, Long activityId);

    void deleteActivity(Long activityId);
}
