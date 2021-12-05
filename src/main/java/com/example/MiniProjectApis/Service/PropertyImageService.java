package com.example.MiniProjectApis.Service;

import com.example.MiniProjectApis.Dtos.AddPropertyImage;
import com.example.MiniProjectApis.Entities.PropertyImages;

public interface PropertyImageService {
    PropertyImages addPropertyImages(AddPropertyImage addPropertyImage);

    void deletePropertyImages(Long imageId);
}
