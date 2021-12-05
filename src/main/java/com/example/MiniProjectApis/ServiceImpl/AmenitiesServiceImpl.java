package com.example.MiniProjectApis.ServiceImpl;

import com.example.MiniProjectApis.CustomException.AlreadyExistsException;
import com.example.MiniProjectApis.CustomException.BadRequestException;
import com.example.MiniProjectApis.CustomException.NotFoundException;
import com.example.MiniProjectApis.Dtos.AddAmenities;
import com.example.MiniProjectApis.Dtos.AddAmenityToActivity;
import com.example.MiniProjectApis.Dtos.AmenityListDto;
import com.example.MiniProjectApis.Entities.Activity;
import com.example.MiniProjectApis.Entities.ActivityAmenities;
import com.example.MiniProjectApis.Entities.Amenities;
import com.example.MiniProjectApis.Repository.ActivityAmenityRepository;
import com.example.MiniProjectApis.Repository.ActivityRepository;
import com.example.MiniProjectApis.Repository.AmenitiesRepository;
import com.example.MiniProjectApis.Service.AmenitiesService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AmenitiesServiceImpl implements AmenitiesService {

    private final AmenitiesRepository amenitiesRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    private final ActivityRepository activityRepository;

    private final ActivityAmenityRepository activityAmenityRepository;

    @Override
    public Amenities addAmenities(AddAmenities addAmenity) {
        Amenities amenities = modelMapper.map(addAmenity, Amenities.class);
        amenitiesRepository.save(amenities);
        return amenities;
    }

    @Override
    public Amenities updateAmenities(AddAmenities addAmenity, Long amenityId) {
        Amenities amenities = amenitiesRepository.findById(amenityId).orElseThrow(() ->
                new NotFoundException(HttpStatus.NOT_FOUND, "Amenity doesn't exists."));
        amenities.setName(addAmenity.getName());
        amenities.setIcon(addAmenity.getIcon());
        amenitiesRepository.save(amenities);
        return amenities;
    }

    @Override
    public Amenities viewAmenities(Long amenityId) {
        return amenitiesRepository.findById(amenityId).orElseThrow(() ->
                new NotFoundException(HttpStatus.NOT_FOUND, "Amenity doesn't exists."));
    }

    @Override
    public ActivityAmenities addAmenityToActivity(AddAmenityToActivity addAmenityToActivity) {
        Activity activity = activityRepository.findById(addAmenityToActivity.getActivityId()).orElseThrow(()->
                new NotFoundException(HttpStatus.NOT_FOUND, "Activity doesn't exists"));
        Amenities amenities = amenitiesRepository.findById(addAmenityToActivity.getAmenityId()).orElseThrow(()->
                new NotFoundException(HttpStatus.NOT_FOUND, "Amenity doesn't exists"));
        ActivityAmenities activityAmenities1 = activityAmenityRepository.getByActivityIdAndAmenityId(addAmenityToActivity.getActivityId(),
                addAmenityToActivity.getAmenityId());
        if(activity.getDeletedTimeStamp() == null){
            if(amenities.getDeletedTimeStamp() == null){
                if(activityAmenities1 == null){
                    ActivityAmenities activityAmenities = new ActivityAmenities();
                    activityAmenities.setActivity(activity);
                    activityAmenities.setAmenities(amenities);
                    activityAmenityRepository.save(activityAmenities);
                }else{
                    throw new AlreadyExistsException(HttpStatus.CONFLICT, "Amenity already exists in property.");
                }

            }else {
                throw new BadRequestException(HttpStatus.BAD_REQUEST, "Amenity is not active.");
            }

        }else {
            throw new BadRequestException(HttpStatus.BAD_REQUEST, "Activity is not active.");
        }
        return null;
    }

    @Override
    public Long getCountOfAmenities() {
        return amenitiesRepository.countOfAmenities();
    }

    @Override
    public List<AmenityListDto> getListOfAmenities() {
        List<Amenities> amenities = amenitiesRepository.findAll();
        List<AmenityListDto> amenityList = amenities.stream().map((Amenities a)->
                new AmenityListDto(
                        a.getName(),
                        a.getIcon()
                )).collect(Collectors.toList());
        return amenityList;
    }

    @Override
    public void deleteAmenity(Long amenityId) {
        Amenities amenities = amenitiesRepository.findById(amenityId).orElseThrow(()->
                new NotFoundException(HttpStatus.NOT_FOUND, "Amenity doesn't exists."));
        List<ActivityAmenities> activityAmenities = activityAmenityRepository.findByAmenityId(amenityId);
        activityAmenities.forEach(activityAmenities1 -> {
            activityAmenities1.setDeletedTimeStamp(new Date());
            activityAmenityRepository.save(activityAmenities1);
        });
        amenities.setDeletedTimeStamp(new Date());
        amenitiesRepository.save(amenities);
    }
}
