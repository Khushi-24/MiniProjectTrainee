package com.example.MiniProjectApis.Service;

import com.example.MiniProjectApis.Dtos.PropertyDto;
import com.example.MiniProjectApis.Dtos.ViewPropertyDetailsResponseDto;
import com.example.MiniProjectApis.Entities.Property;

public interface PropertyService {
    PropertyDto addProperty(PropertyDto propertyDto);

    Property updateProperty(PropertyDto propertyDto, Long propertyId);

    ViewPropertyDetailsResponseDto viewPropertyDetails(Long propertyId);

    void deleteProperty(Long propertyId);
}
