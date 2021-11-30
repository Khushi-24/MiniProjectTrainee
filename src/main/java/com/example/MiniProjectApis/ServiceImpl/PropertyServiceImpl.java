package com.example.MiniProjectApis.ServiceImpl;

import com.example.MiniProjectApis.CustomException.BadRequestException;
import com.example.MiniProjectApis.CustomException.NotFoundException;
import com.example.MiniProjectApis.Dtos.PropertyDto;
import com.example.MiniProjectApis.Dtos.ViewPropertyDetailsResponseDto;
import com.example.MiniProjectApis.Dtos.ViewImageResponseDto;
import com.example.MiniProjectApis.Entities.Property;
import com.example.MiniProjectApis.Entities.PropertyAndActivityImages;
import com.example.MiniProjectApis.Entities.User;
import com.example.MiniProjectApis.Repository.PropertyAndActivityImageRepository;
import com.example.MiniProjectApis.Repository.PropertyRepository;
import com.example.MiniProjectApis.Repository.UserRepository;
import com.example.MiniProjectApis.Service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PropertyServiceImpl implements PropertyService {

    private final ModelMapper modelMapper = new ModelMapper();

    private final PropertyRepository propertyRepository;

    private final UserRepository userRepository;

    private final PropertyAndActivityImageRepository propertyImageRepository;

    @Override
    public Property addProperty(PropertyDto propertyDto) {
        User user = userRepository.findById(propertyDto.getUserId()).orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND,
                "Land Owner doesn't exists."));
        if(user.getRole().equals("Land Owner")){
            if(user.getDeletedTimeStamp() == null){
                Property property = modelMapper.map(propertyDto, Property.class);
                propertyRepository.save(property);
//                List<PropertyImageDto> propertyImageDtoList = propertyDto.getPropertyImageDto();
//                propertyImageDtoList.forEach((e) ->{
//                    PropertyAndActivityImages propertyAndActivityImages = new PropertyAndActivityImages();
//                    propertyAndActivityImages.setProperty(property);
//                    propertyAndActivityImages.setImageName(e.getImageName());
//                    propertyImageRepository.save(propertyAndActivityImages);
//                });
                return property;
            }
            else{
                throw new BadRequestException(HttpStatus.BAD_REQUEST, "Land Owner is not Active.");
            }
        }else {
            throw new BadRequestException(HttpStatus.BAD_REQUEST, "Id doesn't represent a Land Owner.");
        }

    }

    @Override
    public Property updateProperty(PropertyDto propertyDto, Long propertyId) {
        Property property = propertyRepository.findById(propertyId).orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND,
                "Property doesn't exists."));
        return addProperty(propertyDto);
    }

    @Override
    public ViewPropertyDetailsResponseDto viewPropertyDetails(Long propertyId) {
        Property property = propertyRepository.findById(propertyId).orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND,
                "Property doesn't exists."));
        ViewPropertyDetailsResponseDto detailsResponseDto = new ViewPropertyDetailsResponseDto();
        detailsResponseDto.setPropertyId(property.getPropertyId());
        detailsResponseDto.setPropertyArea(property.getPropertyArea());
        detailsResponseDto.setPropertyAddress(property.getPropertyAddress());
        detailsResponseDto.setUserId(property.getUserId());
        detailsResponseDto.setPropertyName(property.getPropertyName());
        List<PropertyAndActivityImages> list = propertyImageRepository.findByPropertyId(propertyId);
        List<ViewImageResponseDto> list1 = list.stream().map((PropertyAndActivityImages p) ->
                new ViewImageResponseDto(
                        p.getImageId(),
                        p.getImageName()
                )).collect(Collectors.toList());
        detailsResponseDto.setPropertyImageResponseDto(list1);
        return detailsResponseDto;
    }

    @Override
    public void deleteProperty(Long propertyId) {
        Property property = propertyRepository.findById(propertyId).orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND,
                "Property doesn't exists."));
        List<PropertyAndActivityImages> images = propertyImageRepository.findByPropertyId(propertyId);
        images.forEach(image ->{
            image.setDeletedTimeStamp(new Date());
            propertyImageRepository.save(image);
        });
        property.setDeletedTimeStamp(new Date());
        propertyRepository.save(property);
    }
}
