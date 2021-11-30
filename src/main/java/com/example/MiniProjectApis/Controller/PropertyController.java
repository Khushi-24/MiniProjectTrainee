package com.example.MiniProjectApis.Controller;

import com.example.MiniProjectApis.Dtos.PropertyDto;
import com.example.MiniProjectApis.Dtos.ViewPropertyDetailsResponseDto;
import com.example.MiniProjectApis.Entities.Property;
import com.example.MiniProjectApis.Service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class PropertyController {

    private final PropertyService propertyService;

    //add property
    @PostMapping("/addProperty")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<?> addProperty(@Valid @RequestBody PropertyDto propertyDto){
        Property property = propertyService.addProperty(propertyDto);
        return new ResponseEntity<>(property, HttpStatus.CREATED);
    }

    //edit property
    @PutMapping("/updateProperty/{propertyId}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<?> updateProperty(@Valid @RequestBody PropertyDto propertyDto, @PathVariable Long propertyId){
        Property property = propertyService.updateProperty(propertyDto, propertyId);
        return new ResponseEntity<>(property, HttpStatus.CREATED);
    }

    //view property details
    @GetMapping("/viewPropertyDetails/{propertyId}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<?> viewPropertyDetails(@PathVariable Long propertyId){
        ViewPropertyDetailsResponseDto details = propertyService.viewPropertyDetails(propertyId);
        return new ResponseEntity<>(details, HttpStatus.CREATED);
    }

    //delete property
    @DeleteMapping("/deleteProperty/{propertyId}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<?> deleteProperty(@PathVariable Long propertyId){
        propertyService.deleteProperty(propertyId);
        return ResponseEntity.ok("Property deleted successfully.");
    }

}
