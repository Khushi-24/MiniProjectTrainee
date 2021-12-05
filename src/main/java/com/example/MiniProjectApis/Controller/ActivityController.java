package com.example.MiniProjectApis.Controller;

import com.example.MiniProjectApis.Common.ApiResponse;
import com.example.MiniProjectApis.Dtos.AddActivityDto;
import com.example.MiniProjectApis.Dtos.ListOfActivityDto;
import com.example.MiniProjectApis.Dtos.UpdateActivityDto;
import com.example.MiniProjectApis.Dtos.ViewActivityDto;
import com.example.MiniProjectApis.Service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class ActivityController {

    private final ActivityService activityService;

    //add Activity
    @PostMapping("/addActivity")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<?> addActivity(@Valid @RequestBody AddActivityDto addActivityDto){
        AddActivityDto dto = activityService.addActivity(addActivityDto);
        return ApiResponse.sendOkResponse("Success", dto);
    }

    //get Count of Activities
    @GetMapping("/getCountOfActivities")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<?> getCountOfActivities(){
        Long countOfActivities = activityService.getCountOfActivities();
        return ApiResponse.sendOkResponse("Success", countOfActivities);
    }

    //get List of Activities
    @GetMapping("/getListOfActivities")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<?> getListOfActivities(){
        List<ListOfActivityDto> listOfActivities = activityService.getListOfActivities();
        return ApiResponse.sendOkResponse("Success", listOfActivities);
    }

    //get List of Activities By PropertyId
    @GetMapping("/getListOfActivityByProperty/{propertyId}")
    @PreAuthorize("hasRole('User')")
    public ResponseEntity<?> getListOfActivityByProperty(@PathVariable Long propertyId){
        List<ListOfActivityDto> listOfActivities = activityService.getListOfActivityByProperty(propertyId);
        return ApiResponse.sendOkResponse("Success", listOfActivities);
    }

    //view Activity
    @GetMapping("/viewActivity/{activityId}")
    @PreAuthorize("hasAnyRole('Admin','User')")
    public ResponseEntity<?> viewActivity(@PathVariable Long activityId){
        ViewActivityDto dto = activityService.viewActivity(activityId);
        return ApiResponse.sendOkResponse("Success", dto);
    }

    //update Activity
    @PutMapping("/updateActivity/{activityId}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<?> updateActivity(@Valid @RequestBody UpdateActivityDto updateActivityDto, @PathVariable Long activityId){
        activityService.updateActivity(updateActivityDto, activityId);
        return ApiResponse.sendOkResponse("Success", null);
    }

    //delete activity
    @DeleteMapping("/deleteActivity/{activityId}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<?> deleteActivity(@PathVariable Long activityId){
        activityService.deleteActivity(activityId);
        return ApiResponse.sendOkResponse("Success", null);
    }

}
