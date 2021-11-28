package com.example.MiniProjectApis.ServiceImpl;

import com.example.MiniProjectApis.CustomException.BadRequestException;
import com.example.MiniProjectApis.Dtos.AddPropertyImage;
import com.example.MiniProjectApis.Entities.Property;
import com.example.MiniProjectApis.Entities.PropertyAndActivityImages;
import com.example.MiniProjectApis.Repository.PropertyAndActivityImageRepository;
import com.example.MiniProjectApis.Repository.PropertyRepository;
import com.example.MiniProjectApis.Service.PropertyAndActivityImageService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PropertyAndActivityImageServiceImpl implements PropertyAndActivityImageService {

    private final ModelMapper modelMapper = new ModelMapper();

    private final PropertyRepository propertyRepository;

    private final PropertyAndActivityImageRepository propertyAndActivityImageRepository;

    @Override
    public PropertyAndActivityImages addPropertyImages(AddPropertyImage addPropertyImage) {
        Property property = propertyRepository.findById(addPropertyImage.getPropertyId()).orElseThrow(() ->
                new BadRequestException(HttpStatus.BAD_REQUEST, "Property doesn't exists."));
        if(property.getDeletedTimeStamp() == null){
            if(propertyAndActivityImageRepository.countByPropertyId(addPropertyImage.getPropertyId()) <= 6){
                System.out.println(propertyAndActivityImageRepository.countByPropertyId((addPropertyImage.getPropertyId())));
//                PropertyAndActivityImages propertyImage = modelMapper.map(addPropertyImage, PropertyAndActivityImages.class);
//                propertyImage.setProperty(property);
//                propertyImage.setCreatedTimeStamp(new Date());
//                propertyAndActivityImageRepository.save(propertyImage);
                PropertyAndActivityImages propertyImage = new PropertyAndActivityImages();
                propertyImage.setProperty(property);
                propertyImage.setImageName(addPropertyImage.getImageName());
                propertyAndActivityImageRepository.save(propertyImage);
                System.out.println("hello" +propertyImage.getId());
                return propertyImage;
            }else {
                throw new BadRequestException(HttpStatus.BAD_REQUEST, "Can't add more than 6 images of a property.");
            }
        }else {
            throw new BadRequestException(HttpStatus.BAD_REQUEST, "Property is not active.");
        }
    }
}
