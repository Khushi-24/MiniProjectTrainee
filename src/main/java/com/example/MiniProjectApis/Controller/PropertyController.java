package com.example.MiniProjectApis.Controller;

import com.example.MiniProjectApis.Dtos.AdminDto;
import com.example.MiniProjectApis.Dtos.PropertyDto;
import com.example.MiniProjectApis.Service.PropertyService;
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
public class PropertyController {

    private final PropertyService propertyService;

    //add property
    @PostMapping("/addProperty")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<?> addProperty(@Valid @RequestBody PropertyDto propertyDto){
        PropertyDto dto = propertyService.addProperty(propertyDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
}
