package com.example.MiniProjectApis.Dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookingDetailsDto {

    private Long bookingId;

    private String date;

    private Long noOfPerson;

    private Long activityId;

    private String activityName;

    private String aboutActivity;

    private String shortDescription;

    private Long propertyId;

    private Long landOwnerId;

    private String landOwnerName;

    private String landOwnerEmail;

    private String landOwnerPhoneNumber;

    private List<ViewAmenitiesDto> viewAmenityList;

}
