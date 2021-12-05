package com.example.MiniProjectApis.Controller;

import com.example.MiniProjectApis.Common.ApiResponse;
import com.example.MiniProjectApis.Common.ListResponse;
import com.example.MiniProjectApis.Dtos.FilterByDistanceDto;
import com.example.MiniProjectApis.Dtos.PropertyDto;
import com.example.MiniProjectApis.Dtos.PropertyListResponseDto;
import com.example.MiniProjectApis.Dtos.ViewPropertyDetailsResponseDto;
import com.example.MiniProjectApis.Entities.Property;
import com.example.MiniProjectApis.Service.PropertyService;
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
public class PropertyController {

    private final PropertyService propertyService;

    //get List of Property
    @GetMapping("/getPropertyList")
    @PreAuthorize("hasAnyRole('Admin','User')")
    public ResponseEntity<?> getPropertyList(){
        List<PropertyListResponseDto> dto = propertyService.getPropertyList();
        return ApiResponse.sendOkResponse("Success", dto);
    }

    //get Count of Properties
    @GetMapping("/getCountOfProperties")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<?> getCountOfProperties(){
        Long countOfProperties = propertyService.getCountOfProperties();
        return ApiResponse.sendOkResponse("Success", countOfProperties);
    }

    //add property
    @PostMapping("/addProperty")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<?> addProperty(@Valid @RequestBody PropertyDto propertyDto){
        PropertyDto dto = propertyService.addProperty(propertyDto);
        return ApiResponse.sendOkResponse("Success", dto);
    }

    //edit property
    @PutMapping("/updateProperty/{propertyId}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<?> updateProperty(@Valid @RequestBody PropertyDto propertyDto, @PathVariable Long propertyId){
        propertyService.updateProperty(propertyDto, propertyId);
        return ApiResponse.sendOkResponse("Success", null);
    }

    //view property details
    @GetMapping("/viewPropertyDetails/{propertyId}")
    @PreAuthorize("hasAnyRole('Admin','User')")
    public ResponseEntity<?> viewPropertyDetails(@PathVariable Long propertyId){
        ViewPropertyDetailsResponseDto details = propertyService.viewPropertyDetails(propertyId);
        return ApiResponse.sendOkResponse("Success", details);
    }

    //delete property
    @DeleteMapping("/deleteProperty/{propertyId}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<?> deleteProperty(@PathVariable Long propertyId){
        propertyService.deleteProperty(propertyId);
        return ApiResponse.sendOkResponse("Success", null);
    }


    //add property By distance
    @PostMapping("/getPropertyByDistance")
    @PreAuthorize("hasRole('User')")
    public ResponseEntity<?> getPropertyByDistance(@Valid @RequestBody FilterByDistanceDto filterByDistanceDto){
        List<PropertyListResponseDto> dto = propertyService.getPropertyByDistance(filterByDistanceDto);
        return ApiResponse.sendOkResponse("Success", dto);
    }

}
