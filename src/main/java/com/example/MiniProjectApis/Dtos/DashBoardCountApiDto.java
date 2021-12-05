package com.example.MiniProjectApis.Dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DashBoardCountApiDto {

    Long countOfUsers;
    Long countOfLandOwners;
    Long countOfAdmin;
    Long countOfProperty;
    Long countOfAmenities;
    Long countOfBookings;
    Long countOfActivities;
}
