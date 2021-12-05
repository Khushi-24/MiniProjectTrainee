package com.example.MiniProjectApis.Controller;

import com.example.MiniProjectApis.Common.ApiResponse;
import com.example.MiniProjectApis.Dtos.AddAmenities;
import com.example.MiniProjectApis.Dtos.AddAmenityToActivity;
import com.example.MiniProjectApis.Dtos.AmenityListDto;
import com.example.MiniProjectApis.Dtos.ListOfActivityDto;
import com.example.MiniProjectApis.Entities.ActivityAmenities;
import com.example.MiniProjectApis.Entities.Amenities;
import com.example.MiniProjectApis.Service.AmenitiesService;
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
public class AmenitiesController {

    private final AmenitiesService amenitiesService;

    // add amenities
    @PostMapping("/addAmenities")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<?> addAmenities(@Valid @RequestBody AddAmenities addAmenity){
        Amenities amenities = amenitiesService.addAmenities(addAmenity);
        return ApiResponse.sendOkResponse("Success", amenities);
    }

    //get List of amenities
    @GetMapping("/getListOfAmenities")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<?> getListOfAmenities(){
        List<AmenityListDto> listOfAmenities = amenitiesService.getListOfAmenities();
        return ApiResponse.sendOkResponse("Success", listOfAmenities);
    }

    //get Count of amenities
    @GetMapping("/getCountOfAmenities")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<?> getCountOfAmenities(){
        Long countOfAmenities = amenitiesService.getCountOfAmenities();
        return ApiResponse.sendOkResponse("Success", countOfAmenities);
    }

    //edit amenities
    @PostMapping("/updateAmenities/{amenityId}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<?> updateAmenities(@Valid @RequestBody AddAmenities addAmenity, @PathVariable Long amenityId){
        Amenities amenities = amenitiesService.updateAmenities(addAmenity, amenityId);
        return ApiResponse.sendOkResponse("Success", amenities);
    }

    //view amenities
    @PutMapping("/viewAmenities/{amenityId}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<?> viewAmenities(@PathVariable Long amenityId){
        Amenities amenities = amenitiesService.viewAmenities(amenityId);
        return ApiResponse.sendOkResponse("Success", amenities);
    }

    // add amenities to Activity
    @PostMapping("/addAmenityToActivity")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<?> addAmenityToActivity(@Valid @RequestBody AddAmenityToActivity addAmenityToActivity){
        ActivityAmenities response = amenitiesService.addAmenityToActivity(addAmenityToActivity);
        return ApiResponse.sendOkResponse("Success", response);
    }

    @DeleteMapping("/deleteAmenity/{amenityId}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<?> deleteAmenity(@PathVariable Long amenityId){
        amenitiesService.deleteAmenity(amenityId);
        return ApiResponse.sendOkResponse("Success", null);
    }
}
