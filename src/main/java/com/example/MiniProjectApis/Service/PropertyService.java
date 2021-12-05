package com.example.MiniProjectApis.Service;

import com.example.MiniProjectApis.Dtos.FilterByDistanceDto;
import com.example.MiniProjectApis.Dtos.PropertyDto;
import com.example.MiniProjectApis.Dtos.PropertyListResponseDto;
import com.example.MiniProjectApis.Dtos.ViewPropertyDetailsResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PropertyService {
    PropertyDto addProperty(PropertyDto propertyDto);

    void updateProperty(PropertyDto propertyDto, Long propertyId);

    ViewPropertyDetailsResponseDto viewPropertyDetails(Long propertyId);

    void deleteProperty(Long propertyId);

    List<PropertyListResponseDto> getPropertyList();

    Long getCountOfProperties();

    List<PropertyListResponseDto> getPropertyByDistance(FilterByDistanceDto filterByDistanceDto);
}
