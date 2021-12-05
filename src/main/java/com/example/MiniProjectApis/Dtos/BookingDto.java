package com.example.MiniProjectApis.Dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class BookingDto {

    private Long bookingId;

    @NotNull(message = "Booking Date can't be null or empty")
    @NotEmpty(message = "Booking Date can't be null or empty")
    private String date;

    @NotNull(message = "No. of persons can't be null or empty")
    private Long noOfPerson;

    @NotNull(message = "activity Id can't be null or empty")
    private Long activityId;

    @NotNull(message = "user Id can't be null or empty")
    private Long userId;

}
