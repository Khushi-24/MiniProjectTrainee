package com.example.MiniProjectApis.Service;

import com.example.MiniProjectApis.Dtos.AddAmenities;
import com.example.MiniProjectApis.Dtos.AddAmenityToActivity;
import com.example.MiniProjectApis.Dtos.AmenityListDto;
import com.example.MiniProjectApis.Entities.ActivityAmenities;
import com.example.MiniProjectApis.Entities.Amenities;

import java.util.List;

public interface AmenitiesService {
    Amenities addAmenities(AddAmenities addAmenity);

    Amenities updateAmenities(AddAmenities addAmenity, Long amenityId);

    Amenities viewAmenities(Long amenityId);

    ActivityAmenities addAmenityToActivity(AddAmenityToActivity addAmenityToActivity);

    Long getCountOfAmenities();

    List<AmenityListDto> getListOfAmenities();

    void deleteAmenity(Long amenityId);
}
