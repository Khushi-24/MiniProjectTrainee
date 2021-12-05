package com.example.MiniProjectApis.ServiceImpl;

import com.example.MiniProjectApis.CustomException.BadRequestException;
import com.example.MiniProjectApis.Dtos.AddActivityImageDto;
import com.example.MiniProjectApis.Entities.Activity;
import com.example.MiniProjectApis.Entities.ActivityImages;
import com.example.MiniProjectApis.Entities.Property;
import com.example.MiniProjectApis.Entities.PropertyImages;
import com.example.MiniProjectApis.Repository.ActivityImageRepository;
import com.example.MiniProjectApis.Repository.ActivityRepository;
import com.example.MiniProjectApis.Repository.PropertyRepository;
import com.example.MiniProjectApis.Service.ActivityImageService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class ActivityImageServiceImpl implements ActivityImageService {

    private final ModelMapper modelMapper = new ModelMapper();

    private final ActivityRepository activityRepository;

    private final ActivityImageRepository activityImageRepository;

    @Override
    public ActivityImages addActivityImages(AddActivityImageDto addActivityImageDto) {
        Activity activity = activityRepository.findById(addActivityImageDto.getActivityId()).orElseThrow(() ->
                new BadRequestException(HttpStatus.BAD_REQUEST, "Activity doesn't exists."));
        if(activity.getDeletedTimeStamp() == null){
            if(activityImageRepository.countByActivityId(addActivityImageDto.getActivityId()) < 6){
                ActivityImages images = new ActivityImages();
                images.setActivity(activity);
                images.setImageName(addActivityImageDto.getImageName());
                activityImageRepository.save(images);
                images.setActivityId(activity.getPropertyId());
                return images;
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
            ActivityImages image = activityImageRepository.findById(imageId).orElseThrow(() ->
                    new BadRequestException(HttpStatus.BAD_REQUEST, "Image doesn't exists"));
            image.setDeletedTimeStamp(new Date());
            activityImageRepository.save(image);
        }else {
            throw new BadRequestException(HttpStatus.BAD_REQUEST, "ImageId can't be null");
        }
    }
}
