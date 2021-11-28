package com.example.MiniProjectApis.Service;

import com.example.MiniProjectApis.Dtos.AddPropertyImage;
import com.example.MiniProjectApis.Entities.PropertyAndActivityImages;

public interface PropertyAndActivityImageService {
    PropertyAndActivityImages addPropertyImages(AddPropertyImage addPropertyImage);
}
