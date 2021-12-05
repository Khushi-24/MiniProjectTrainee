package com.example.MiniProjectApis.ServiceImpl;

import com.example.MiniProjectApis.CustomException.BadRequestException;
import com.example.MiniProjectApis.CustomException.NotFoundException;
import com.example.MiniProjectApis.Dtos.*;
import com.example.MiniProjectApis.Entities.*;
import com.example.MiniProjectApis.Repository.*;
import com.example.MiniProjectApis.Service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActivityServiceImpl implements ActivityService {

    private final ModelMapper modelMapper = new ModelMapper();

    private final PropertyRepository propertyRepository;

    private final ActivityImageRepository activityImageRepository;

    private final ActivityRepository activityRepository;

    private final AmenitiesRepository amenitiesRepository;

    private final ActivityAmenityRepository activityAmenityRepository;

    @Override
    public AddActivityDto addActivity(AddActivityDto addActivityDto) {
        Property property = propertyRepository.findById(addActivityDto.getPropertyId()).orElseThrow(() ->
                new BadRequestException(HttpStatus.BAD_REQUEST, "Property doesn't exists."));
        if(property.getDeletedTimeStamp() == null){
            if(addActivityDto.getImageDtoList().size() > 6){
                throw new BadRequestException(HttpStatus.BAD_REQUEST, "Can't add more than 6 images of a activity.");
            }else {
                if(addActivityDto.getAmenitiesIdList().size() == 0){
                    throw new BadRequestException(HttpStatus.BAD_REQUEST, "Amenities are required");
                }else{
                    List<Long> amenityIdList = addActivityDto.getAmenitiesIdList().stream().map(ActivityAmenitiesDto::getAmenityId).collect(Collectors.toList());
                    Set<Long> idList = new HashSet<>(amenityIdList);
                    idList.forEach(id ->
                    {
                        Amenities amenities = amenitiesRepository.findById(id).orElseThrow(() ->
                                new NotFoundException(HttpStatus.NOT_FOUND, "Amenity doesn't exists."));
                        if(amenities.getDeletedTimeStamp() != null){
                            throw new BadRequestException(HttpStatus.BAD_REQUEST, "Amenity is not available.");
                        }
                    });
                    Activity activity = modelMapper.map(addActivityDto, Activity.class);
                    activityRepository.save(activity);
                    idList.forEach(id ->
                    {
                        ActivityAmenities activityAmenities = new ActivityAmenities();
                        activityAmenities.setActivity(activity);
                        Amenities amenities = amenitiesRepository.getById(id);
                        activityAmenities.setAmenities(amenities);
                        activityAmenityRepository.save(activityAmenities);

                    });
                    addActivityDto.setActivityId(activity.getActivityId());
                    List<ImageDto> imageDtoList = addActivityDto.getImageDtoList();
                    imageDtoList.forEach(imageDto -> {
                        ActivityImages images = new ActivityImages();
                        images.setImageName(imageDto.getImageName());
                        images.setActivity(activity);
                        activityImageRepository.save(images);
                    });
                }
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
        List<ActivityImages> list = activityImageRepository.findByActivityId(activityId);
        List<ViewImageResponseDto> list1 = list.stream().map((ActivityImages p) ->
                new ViewImageResponseDto(
                        p.getImageId(),
                        p.getImageName()
                )).collect(Collectors.toList());
        List<Amenities> amenities = amenitiesRepository.amenityList(activityId);
        List<ViewAmenitiesDto> amenitiesDtoList = amenities.stream().map((Amenities a) ->
                new ViewAmenitiesDto(
                        a.getId(),
                        a.getIcon(),
                        a.getName()
                )).collect(Collectors.toList());
        viewActivityDto.setImageDtoList(list1);
        viewActivityDto.setAmenitiesList(amenitiesDtoList);
        return viewActivityDto;
    }

    @Override
    public void updateActivity(UpdateActivityDto updateActivityDto, Long activityId) {
        Activity activity = activityRepository.findById(activityId).orElseThrow(() ->
                new BadRequestException(HttpStatus.NOT_FOUND, "Activity doesn't exists."));
        Property property = propertyRepository.findById(updateActivityDto.getPropertyId()).orElseThrow(() ->
                new BadRequestException(HttpStatus.NOT_FOUND, "Property doesn't exists."));
        if(property.getDeletedTimeStamp() == null){
            if(activity.getDeletedTimeStamp() == null){
                activity.setActivityName(updateActivityDto.getActivityName());
                activity.setShortDescription(updateActivityDto.getShortDescription());
                activity.setAboutActivity(updateActivityDto.getAboutActivity());
                activity.setProperty(property);
                activityRepository.save(activity);
                updateActivityDto.setActivityId(activityId);
            }else{
                throw new BadRequestException(HttpStatus.BAD_REQUEST, "Activity is not active.");
            }

        }else{
            throw new BadRequestException(HttpStatus.BAD_REQUEST, "Property is not active.");
        }
    }

    @Override
    public void deleteActivity(Long activityId) {
        Activity activity = activityRepository.findById(activityId).orElseThrow(() ->
                new BadRequestException(HttpStatus.NOT_FOUND, "Activity doesn't exists."));
        List<ActivityImages> images = activityImageRepository.findByActivityId(activityId);
        images.forEach(image ->{
            image.setDeletedTimeStamp(new Date());
            activityImageRepository.save(image);
        });
        activity.setDeletedTimeStamp(new Date());
        activityRepository.save(activity);
    }

    @Override
    public Long getCountOfActivities() {
        return activityRepository.activities();
    }

    @Override
    public List<ListOfActivityDto> getListOfActivities() {
        List<Activity> activities = activityRepository.activityList();
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


}
