package com.example.MiniProjectApis.Service;

import com.example.MiniProjectApis.Dtos.BookingDetailsDto;
import com.example.MiniProjectApis.Dtos.BookingDto;
import com.example.MiniProjectApis.Dtos.BookingListResponseDto;

import java.util.List;

public interface BookingService {
    BookingDto addBooking(BookingDto bookingDto);

    List<BookingListResponseDto> getBookingList(Long userId);

    Long getCountOfBooking();

    List<BookingListResponseDto> getBookingsList();

    BookingDetailsDto getBookingDetails(Long bookingId);
}
