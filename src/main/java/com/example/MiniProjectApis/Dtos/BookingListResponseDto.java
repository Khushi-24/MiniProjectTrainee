package com.example.MiniProjectApis.Dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingListResponseDto {

    private Long bookingId;
    private String date;
    private Long noOfPerson;
    private String activityName;
    private Long activityId;
    private String name;

    public BookingListResponseDto(Long bookingId, String date, Long noOfPerson, String activityName, Long activityId, String name) {

        this.bookingId = bookingId;
        this.date = date;
        this.noOfPerson = noOfPerson;
        this.activityName = activityName;
        this.activityId = activityId;
        this.name = name;
    }
}
