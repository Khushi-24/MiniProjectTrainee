package com.example.MiniProjectApis.Controller;

import com.example.MiniProjectApis.Dtos.AddPropertyImage;
import com.example.MiniProjectApis.Entities.PropertyAndActivityImages;
import com.example.MiniProjectApis.Service.PropertyAndActivityImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class PropertyAndActivityImageController {

    private final PropertyAndActivityImageService propertyAndActivityImageService;

//    @PostMapping("/addPropertyImages/{propertyId}")
//    @PreAuthorize("hasRole('Admin')")
//    public ResponseEntity<?> addPropertyImages(@Valid @RequestBody List<PropertyImageDto> propertyImageDtoList, @PathVariable Long propertyId){
//        List<PropertyImageDto> images = propertyAndActivityImageService.addPropertyImages(propertyImageDtoList, propertyId);
//        return new ResponseEntity(images, HttpStatus.CREATED);
//    }

    @PostMapping("/addPropertyImages")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<?> addPropertyImages(@Valid @RequestBody AddPropertyImage addPropertyImage){
        PropertyAndActivityImages image = propertyAndActivityImageService.addPropertyImages(addPropertyImage);
        return new ResponseEntity(image, HttpStatus.CREATED);

    }

    @DeleteMapping("/deletePropertyImages/{imageId}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<?> deletePropertyImages(@PathVariable Long imageId){
        propertyAndActivityImageService.deletePropertyImages(imageId);
        return ResponseEntity.ok("Image Deleted successfully.");

    }

}
