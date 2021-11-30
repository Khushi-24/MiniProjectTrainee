package com.example.MiniProjectApis.Controller;

import com.example.MiniProjectApis.Dtos.AddActivityDto;
import com.example.MiniProjectApis.Dtos.PropertyDto;
import com.example.MiniProjectApis.Dtos.UpdateActivityDto;
import com.example.MiniProjectApis.Dtos.ViewActivityDto;
import com.example.MiniProjectApis.Entities.Activity;
import com.example.MiniProjectApis.Entities.Property;
import com.example.MiniProjectApis.Service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class ActivityController {

    private final ActivityService activityService;

    //add Activity
    @PostMapping("/addActivity")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<?> addActivity(@Valid @RequestBody AddActivityDto addActivityDto){
        AddActivityDto dto = activityService.addActivity(addActivityDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    //view Activity
    @GetMapping("/viewActivity/{activityId}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<?> viewActivity(@PathVariable Long activityId){
        ViewActivityDto dto = activityService.viewActivity(activityId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    //update Activity
    @PutMapping("/updateActivity/{activityId}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<?> updateActivity(@Valid @RequestBody UpdateActivityDto updateActivityDto, @PathVariable Long activityId){
        UpdateActivityDto dto = activityService.updateActivity(updateActivityDto, activityId);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    //delete activity
    @DeleteMapping("/deleteActivity/{activityId}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<?> deleteActivity(@PathVariable Long activityId){
        activityService.deleteActivity(activityId);
        return ResponseEntity.ok("Activity deleted successfully.");
    }

}
