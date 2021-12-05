package com.example.MiniProjectApis.Controller;

import com.example.MiniProjectApis.Common.ApiResponse;
import com.example.MiniProjectApis.Dtos.AddActivityImageDto;
import com.example.MiniProjectApis.Entities.ActivityImages;
import com.example.MiniProjectApis.Service.ActivityImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class ActivityImageController {

    private final ActivityImageService activityImageService;

    @PostMapping("/addActivityImages")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<?> addActivityImages(@Valid @RequestBody AddActivityImageDto addActivityImageDto){
        ActivityImages image = activityImageService.addActivityImages(addActivityImageDto);
        return ApiResponse.sendOkResponse("Success", image);
    }

    @DeleteMapping("/deleteActivityImages/{imageId}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<?> deleteActivityImages(@PathVariable Long imageId){
        activityImageService.deletePropertyImages(imageId);
        return ApiResponse.sendOkResponse("Success", null);
    }
}
