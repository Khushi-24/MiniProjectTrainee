package com.example.MiniProjectApis.Controller;

import com.example.MiniProjectApis.Dtos.AddPropertyImage;
import com.example.MiniProjectApis.Entities.PropertyAndActivityImages;
import com.example.MiniProjectApis.Service.PropertyAndActivityImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class PropertyAndActivityImageController {

    private final PropertyAndActivityImageService propertyAndActivityImageService;

    @PostMapping("/addPropertyImages")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<?> addPropertyImages(@Valid @RequestBody AddPropertyImage addPropertyImage){
         PropertyAndActivityImages image = propertyAndActivityImageService.addPropertyImages(addPropertyImage);
         return new ResponseEntity(image, HttpStatus.CREATED);

    }
}
