package com.example.MiniProjectApis.ServiceImpl;

import com.example.MiniProjectApis.CustomException.BadRequestException;
import com.example.MiniProjectApis.CustomException.NotFoundException;
import com.example.MiniProjectApis.Dtos.*;
import com.example.MiniProjectApis.Entities.*;
import com.example.MiniProjectApis.Repository.*;
import com.example.MiniProjectApis.Service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    private final ActivityRepository activityRepository;

    private final ActivityImageRepository activityImageRepository;

    private final PropertyImageRepository propertyImageRepository;

    @Override
    public PropertyDto addProperty(PropertyDto propertyDto) {
        User user = userRepository.findById(propertyDto.getUserId()).orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND,
                "Land Owner doesn't exists."));
        if(user.getRole().equals("Land Owner")){
            if(user.getDeletedTimeStamp() == null){
                if(propertyDto.getImageDtoList().size() > 6){
                    throw new BadRequestException(HttpStatus.BAD_REQUEST, "Can't add more than 6 images of a activity.");
                }else {
                    Property property = modelMapper.map(propertyDto, Property.class);
                    propertyRepository.save(property);
                    propertyDto.setPropertyId(property.getPropertyId());
                    List<ImageDto> imageDtoList = propertyDto.getImageDtoList();
                    imageDtoList.forEach(imageDto -> {
                        PropertyImages images = new PropertyImages();
                        images.setImageName(imageDto.getImageName());
                        images.setProperty(property);
                        propertyImageRepository.save(images);

                    });
                    return propertyDto;
                }
            }
            else{
                throw new BadRequestException(HttpStatus.BAD_REQUEST, "Land Owner is not Active.");
            }
        }else {
            throw new BadRequestException(HttpStatus.BAD_REQUEST, "Id doesn't represent a Land Owner.");
        }

    }

    @Override
    public void updateProperty(PropertyDto propertyDto, Long propertyId) {
        Property property = propertyRepository.findById(propertyId).orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND,
                "Property doesn't exists."));
        PropertyDto propertyDto1 = addProperty(propertyDto);
        Property property1 = modelMapper.map(propertyDto1, Property.class);
        property1.setPropertyId(propertyId);
        propertyRepository.save(property1);

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
        detailsResponseDto.setImageIcon(property.getImageIcon());
        detailsResponseDto.setDistance(property.getDistance());
        detailsResponseDto.setLandOwnerId(property.getUserId());
        detailsResponseDto.setLandOwnerName(property.getUser().getName());
        detailsResponseDto.setLandOwnerEmail(property.getUser().getEmail());
        detailsResponseDto.setLandOwnerPhoneNumber(property.getUser().getPhoneNumber());
        detailsResponseDto.setLandOwnerAddress(property.getUser().getAddress());
        List<PropertyImages> list = propertyImageRepository.findByPropertyId(propertyId);
        List<ViewImageResponseDto> list1 = list.stream().map((PropertyImages p) ->
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
        List<Activity> activityList = activityRepository.findByPropertyId(propertyId);
        activityList.forEach(activity -> {
            deleteActivityImage(activity.getActivityId());
            activity.setDeletedTimeStamp(new Date());
            activityRepository.save(activity);
        });
        List<PropertyImages> images = propertyImageRepository.findByPropertyId(propertyId);
        images.forEach(image ->{
            image.setDeletedTimeStamp(new Date());
            propertyImageRepository.save(image);
        });
        property.setDeletedTimeStamp(new Date());
        propertyRepository.save(property);
    }

    @Override
    public List<PropertyListResponseDto> getPropertyList() {
        List<Property> propertyList = propertyRepository.getPropertyList();
        List<PropertyListResponseDto> propertyListResponseDto= propertyList.stream().map((Property p) ->
                new PropertyListResponseDto(
                        p.getPropertyId(),
                        p.getPropertyName(),
                        p.getPropertyArea(),
                        p.getPropertyAddress(),
                        p.getUserId(),
                        p.getDistance(),
                        p.getUser().getName()
                        )).collect(Collectors.toList());
        return propertyListResponseDto;
    }

    public List<ListOfActivityDto> getListOfActivityByProperty(Long propertyId) {
        List<Activity> activities = activityRepository.activityListByPropertyId(propertyId);
        return activities.stream().map((Activity a)->
                new ListOfActivityDto(
                        a.getActivityId(),
                        a.getActivityName(),
                        a.getShortDescription(),
                        a.getAboutActivity(),
                        a.getPropertyId(),
                        a.getProperty().getPropertyName()
                )).collect(Collectors.toList());
    }

    @Override
    public Long getCountOfProperties() {
        return propertyRepository.properties();
    }

    @Override
    public List<PropertyListResponseDto> getPropertyByDistance(FilterByDistanceDto filterByDistanceDto) {
        List<Property> propertyList = propertyRepository.getPropertyListByDistance(filterByDistanceDto.getFrom(),
                filterByDistanceDto.getTo());
        return propertyList.stream().map((Property p) ->
                new PropertyListResponseDto(
                        p.getPropertyId(),
                        p.getPropertyName(),
                        p.getPropertyArea(),
                        p.getPropertyAddress(),
                        p.getUserId(),
                        p.getDistance(),
                        p.getUser().getName()
                )).collect(Collectors.toList());
    }


    public void deleteActivityImage(Long activityId) {
        List<ActivityImages> images = activityImageRepository.findByActivityId(activityId);
        images.forEach(image ->{
            image.setDeletedTimeStamp(new Date());
            activityImageRepository.save(image);
        });
    }

}
