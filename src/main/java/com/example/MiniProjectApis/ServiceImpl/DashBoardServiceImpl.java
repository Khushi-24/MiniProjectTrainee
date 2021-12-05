package com.example.MiniProjectApis.ServiceImpl;

import com.example.MiniProjectApis.Dtos.DashBoardCountApiDto;
import com.example.MiniProjectApis.Repository.*;
import com.example.MiniProjectApis.Service.DashBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashBoardServiceImpl implements DashBoardService {

    private final ActivityRepository activityRepository;

    private final PropertyRepository propertyRepository;

    private final AmenitiesRepository amenitiesRepository;

    private final UserRepository userRepository;

    private final BookingRepository bookingRepository;


    @Override
    public DashBoardCountApiDto getCountOfAll() {
        DashBoardCountApiDto dashBoardCountApiDto = new DashBoardCountApiDto();
        dashBoardCountApiDto.setCountOfAdmin(userRepository.countOfAdmin());
        dashBoardCountApiDto.setCountOfUsers(userRepository.countOfUsers());
        dashBoardCountApiDto.setCountOfLandOwners(userRepository.countOfLandOwner());
        dashBoardCountApiDto.setCountOfActivities(activityRepository.activities());
        dashBoardCountApiDto.setCountOfAmenities(amenitiesRepository.countOfAmenities());
        dashBoardCountApiDto.setCountOfBookings(bookingRepository.countOfBookings());
        dashBoardCountApiDto.setCountOfProperty(propertyRepository.properties());
        return dashBoardCountApiDto;
    }
}
