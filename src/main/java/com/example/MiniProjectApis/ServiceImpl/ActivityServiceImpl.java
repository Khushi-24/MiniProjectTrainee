package com.example.MiniProjectApis.ServiceImpl;

import com.example.MiniProjectApis.CustomException.BadRequestException;
import com.example.MiniProjectApis.CustomException.NotFoundException;
import com.example.MiniProjectApis.Dtos.*;
import com.example.MiniProjectApis.Entities.Activity;
import com.example.MiniProjectApis.Entities.Property;
import com.example.MiniProjectApis.Entities.PropertyAndActivityImages;
import com.example.MiniProjectApis.Repository.ActivityRepository;
import com.example.MiniProjectApis.Repository.PropertyAndActivityImageRepository;
import com.example.MiniProjectApis.Repository.PropertyRepository;
import com.example.MiniProjectApis.Service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActivityServiceImpl implements ActivityService {

    private final ModelMapper modelMapper = new ModelMapper();

    private final PropertyRepository propertyRepository;

    private final PropertyAndActivityImageRepository propertyAndActivityImageRepository;

    private final ActivityRepository activityRepository;

    @Override
    public AddActivityDto addActivity(AddActivityDto addActivityDto) {
        Property property = propertyRepository.findById(addActivityDto.getPropertyId()).orElseThrow(() ->
                new BadRequestException(HttpStatus.BAD_REQUEST, "Property doesn't exists."));
        if(property.getDeletedTimeStamp() == null){
            if(addActivityDto.getImageDtoList().size() > 6){
                throw new BadRequestException(HttpStatus.BAD_REQUEST, "Can't add more than 6 images of a activity.");
            }else {
                Activity activity = modelMapper.map(addActivityDto, Activity.class);
                activityRepository.save(activity);
                addActivityDto.setActivityId(activity.getActivityId());
                List<ImageDto> imageDtoList = addActivityDto.getImageDtoList();
                imageDtoList.forEach(imageDto -> {
                    PropertyAndActivityImages images = new PropertyAndActivityImages();
                    images.setImageName(imageDto.getImageName());
                    images.setProperty(property);
                    images.setActivity(activity);
                    propertyAndActivityImageRepository.save(images);
                });
            }
        }else{
            throw new BadRequestException(HttpStatus.BAD_REQUEST, "Property is not active.");
        }

        return addActivityDto;
    }

    @Override
    public ViewActivityDto viewActivity(Long activityId) {
        Activity activity = activityRepository.findById(activityId).orElseThrow(() ->
                new BadRequestException(HttpStatus.BAD_REQUEST, "Activity doesn't exists."));
        ViewActivityDto viewActivityDto = modelMapper.map(activity, ViewActivityDto.class);
        List<PropertyAndActivityImages> list = propertyAndActivityImageRepository.findByActivityId(activityId);
        List<ViewImageResponseDto> list1 = list.stream().map((PropertyAndActivityImages p) ->
                new ViewImageResponseDto(
                        p.getImageId(),
                        p.getImageName()
                )).collect(Collectors.toList());
        viewActivityDto.setImageDtoList(list1);
        return viewActivityDto;
    }

    @Override
    public UpdateActivityDto updateActivity(UpdateActivityDto updateActivityDto, Long activityId) {
        Activity activity = activityRepository.findById(activityId).orElseThrow(() ->
                new BadRequestException(HttpStatus.NOT_FOUND, "Activity doesn't exists."));
        Property property = propertyRepository.findById(updateActivityDto.getPropertyId()).orElseThrow(() ->
                new BadRequestException(HttpStatus.NOT_FOUND, "Property doesn't exists."));
        if(property.getDeletedTimeStamp() == null){
            if(activity.getDeletedTimeStamp() == null){
                activity = modelMapper.map(updateActivityDto, Activity.class);
                activityRepository.save(activity);
                updateActivityDto.setActivityId(activityId);
            }else{
                throw new BadRequestException(HttpStatus.BAD_REQUEST, "Activity is not active.");
            }

        }else{
            throw new BadRequestException(HttpStatus.BAD_REQUEST, "Property is not active.");
        }
        return updateActivityDto;
    }

    @Override
    public void deleteActivity(Long activityId) {
        Activity activity = activityRepository.findById(activityId).orElseThrow(() ->
                new BadRequestException(HttpStatus.NOT_FOUND, "Activity doesn't exists."));
        List<PropertyAndActivityImages> images = propertyAndActivityImageRepository.findByActivityId(activityId);
        images.forEach(image ->{
            image.setDeletedTimeStamp(new Date());
            propertyAndActivityImageRepository.save(image);
        });
        activity.setDeletedTimeStamp(new Date());
        activityRepository.save(activity);
    }


}
