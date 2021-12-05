package com.example.MiniProjectApis.Controller;

import com.example.MiniProjectApis.Common.ApiResponse;
import com.example.MiniProjectApis.Dtos.AddPropertyImage;
import com.example.MiniProjectApis.Entities.PropertyImages;
import com.example.MiniProjectApis.Service.PropertyImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class PropertyImageController {

    private final PropertyImageService propertyImageService;

    @PostMapping("/addPropertyImages")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<?> addPropertyImages(@Valid @RequestBody AddPropertyImage addPropertyImage){
        PropertyImages image = propertyImageService.addPropertyImages(addPropertyImage);
        return ApiResponse.sendOkResponse("Success", image);
    }

    @DeleteMapping("/deletePropertyImages/{imageId}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<?> deletePropertyImages(@PathVariable Long imageId){
        propertyImageService.deletePropertyImages(imageId);
        return ApiResponse.sendOkResponse("Success", null);

    }
}
