package com.example.MiniProjectApis.Service;

import com.example.MiniProjectApis.Dtos.AddActivityImageDto;
import com.example.MiniProjectApis.Entities.ActivityImages;

public interface ActivityImageService {
    ActivityImages addActivityImages(AddActivityImageDto addActivityImageDto);

    void deletePropertyImages(Long imageId);
}
