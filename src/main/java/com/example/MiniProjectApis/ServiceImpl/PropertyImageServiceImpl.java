package com.example.MiniProjectApis.ServiceImpl;

import com.example.MiniProjectApis.Repository.PropertyImageRepository;
import com.example.MiniProjectApis.Service.PropertyImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PropertyImageServiceImpl implements PropertyImageService {

    private final PropertyImageRepository propertyImageRepository;


}
