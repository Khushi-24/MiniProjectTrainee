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

import java.util.Date;

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
            if(propertyAndActivityImageRepository.countByPropertyId(addPropertyImage.getPropertyId()) < 6){
                PropertyAndActivityImages propertyImage = new PropertyAndActivityImages();
                propertyImage.setProperty(property);
                propertyImage.setImageName(addPropertyImage.getImageName());
                propertyAndActivityImageRepository.save(propertyImage);
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
            PropertyAndActivityImages image = propertyAndActivityImageRepository.findById(imageId).orElseThrow(() ->
                    new BadRequestException(HttpStatus.BAD_REQUEST, "Image doesn't exists"));
            image.setDeletedTimeStamp(new Date());
            propertyAndActivityImageRepository.save(image);
        }else {
            throw new BadRequestException(HttpStatus.BAD_REQUEST, "ImageId can't be null");
        }
    }
}

//    @Override
//    public List<PropertyImageDto> addPropertyImages(List<PropertyImageDto> propertyImageDtoList, Long propertyId) {
//        Property property = propertyRepository.findById(propertyId).orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND,
//                "Property doesn't exists."));
//
////        List<PropertyImageDto> propertyImageDtoList = propertyDto.getPropertyImageDto();
//        propertyImageDtoList.forEach((e) ->{
//            PropertyAndActivityImages propertyAndActivityImages = new PropertyAndActivityImages();
//            propertyAndActivityImages.setProperty(property);
//            propertyAndActivityImages.setImageName(e.getImageName());
//            propertyAndActivityImageRepository.save(propertyAndActivityImages);
//        });
//        return propertyImageDtoList;
//    }

