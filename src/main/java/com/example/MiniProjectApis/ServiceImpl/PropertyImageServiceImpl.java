package com.example.MiniProjectApis.ServiceImpl;

import com.example.MiniProjectApis.CustomException.BadRequestException;
import com.example.MiniProjectApis.Dtos.AddPropertyImage;
import com.example.MiniProjectApis.Entities.Property;
import com.example.MiniProjectApis.Entities.PropertyImages;
import com.example.MiniProjectApis.Repository.PropertyImageRepository;
import com.example.MiniProjectApis.Repository.PropertyRepository;
import com.example.MiniProjectApis.Service.PropertyImageService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class PropertyImageServiceImpl implements PropertyImageService {

    private final ModelMapper modelMapper = new ModelMapper();

    private final PropertyRepository propertyRepository;

    private final PropertyImageRepository propertyImageRepository;

    @Override
    public PropertyImages addPropertyImages(AddPropertyImage addPropertyImage) {
        Property property = propertyRepository.findById(addPropertyImage.getPropertyId()).orElseThrow(() ->
                new BadRequestException(HttpStatus.BAD_REQUEST, "Property doesn't exists."));
        if(property.getDeletedTimeStamp() == null){
            if(propertyImageRepository.countByPropertyId(addPropertyImage.getPropertyId()) < 6){
                PropertyImages propertyImage = new PropertyImages();
                propertyImage.setProperty(property);
                propertyImage.setImageName(addPropertyImage.getImageName());
                propertyImageRepository.save(propertyImage);
                propertyImage.setPropertyId(property.getPropertyId());
                return propertyImage;
            }else {
                throw new BadRequestException(HttpStatus.BAD_REQUEST, "Can't add more than 6 images of a property.");
            }
        }else {
            throw new BadRequestException(HttpStatus.BAD_REQUEST, "Property is not active.");
        }
    }

    @Override
    public void deletePropertyImages(Long imageId) {
        if(imageId != null){
            PropertyImages image = propertyImageRepository.findById(imageId).orElseThrow(() ->
                    new BadRequestException(HttpStatus.BAD_REQUEST, "Image doesn't exists"));
            image.setDeletedTimeStamp(new Date());
            propertyImageRepository.save(image);
        }else {
            throw new BadRequestException(HttpStatus.BAD_REQUEST, "ImageId can't be null");
        }
    }
}
