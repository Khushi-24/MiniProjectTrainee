package com.example.MiniProjectApis.Controller;

import com.example.MiniProjectApis.Common.ApiResponse;
import com.example.MiniProjectApis.Common.ListResponse;
import com.example.MiniProjectApis.Dtos.AddActivityDto;
import com.example.MiniProjectApis.Dtos.BookingDetailsDto;
import com.example.MiniProjectApis.Dtos.BookingDto;
import com.example.MiniProjectApis.Dtos.BookingListResponseDto;
import com.example.MiniProjectApis.Service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    //add Booking
    @PostMapping("/addBooking")
    @PreAuthorize("hasRole('User')")
    public ResponseEntity<?> addBooking(@Valid @RequestBody BookingDto bookingDto){
        BookingDto dto = bookingService.addBooking(bookingDto);
        return ApiResponse.sendOkResponse("Success", dto);
    }

    //get Booking List
    @GetMapping("/getBookingList/{userId}")
    @PreAuthorize("hasAnyRole('Admin','User')")
    public ResponseEntity<?> getBookingList(@PathVariable Long userId){
        List<BookingListResponseDto> dto = bookingService.getBookingList(userId);
        return ApiResponse.sendOkResponse("Success", dto);
    }

    //get Booking List(Admin)
    @GetMapping("/getBookingsList")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<?> getBookingsList(){
        List<BookingListResponseDto> dto = bookingService.getBookingsList();
        return ApiResponse.sendOkResponse("Success", dto);
    }

    //get Count of Booking
    @GetMapping("/getCountOfBooking")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<?> getCountOfBooking(){
        Long countOfBooking = bookingService.getCountOfBooking();
        return ApiResponse.sendOkResponse("Success", countOfBooking);
    }

    //get Booking details (User)
    @GetMapping("getBookingDetails/{bookingId}")
    @PreAuthorize("hasRole('User')")
    public ResponseEntity<?> getBookingDetails(@PathVariable Long bookingId){
        BookingDetailsDto  bookingDetailsDto = bookingService.getBookingDetails(bookingId);
        return ApiResponse.sendOkResponse("Success", bookingDetailsDto);
    }
}
