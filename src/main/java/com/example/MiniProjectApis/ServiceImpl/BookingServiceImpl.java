package com.example.MiniProjectApis.ServiceImpl;

import com.example.MiniProjectApis.CustomException.BadRequestException;
import com.example.MiniProjectApis.CustomException.NotFoundException;
import com.example.MiniProjectApis.Dtos.BookingDetailsDto;
import com.example.MiniProjectApis.Dtos.BookingDto;
import com.example.MiniProjectApis.Dtos.BookingListResponseDto;
import com.example.MiniProjectApis.Dtos.ViewAmenitiesDto;
import com.example.MiniProjectApis.Entities.Activity;
import com.example.MiniProjectApis.Entities.Amenities;
import com.example.MiniProjectApis.Entities.Booking;
import com.example.MiniProjectApis.Entities.User;
import com.example.MiniProjectApis.Repository.*;
import com.example.MiniProjectApis.Service.BookingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final UserRepository userRepository;

    private final ActivityRepository activityRepository;

    private final BookingRepository bookingRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    private final AmenitiesRepository amenitiesRepository;


    @Override
    public BookingDto addBooking(BookingDto bookingDto) {
        User user = userRepository.findById(bookingDto.getUserId()).orElseThrow(()->
                new NotFoundException(HttpStatus.NOT_FOUND, "User doesn't exists"));
        Activity activity = activityRepository.findById(bookingDto.getActivityId()).orElseThrow(()->
                new NotFoundException(HttpStatus.NOT_FOUND, "Activity doesn't exists"));
        if(user.getDeletedTimeStamp() == null){
            if(user.getRole().equals("User")){
                if(activity.getDeletedTimeStamp() == null){
                    Booking booking = modelMapper.map(bookingDto, Booking.class);
                    bookingRepository.save(booking);
                    bookingDto.setBookingId(booking.getBookingId());
                    return bookingDto;
                }else{
                    throw new BadRequestException(HttpStatus.BAD_REQUEST, "Activity is not Active.");
                }
            }else {
                throw new BadRequestException(HttpStatus.BAD_REQUEST, "Id doesn't represent user.");
            }
        }else{
            throw new BadRequestException(HttpStatus.BAD_REQUEST, "User is not Active.");
        }
    }

    @Override
    public List<BookingListResponseDto> getBookingList(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()->
                new NotFoundException(HttpStatus.NOT_FOUND, "User doesn't exists"));
        if(user.getDeletedTimeStamp() == null){
            if(user.getRole().equals("User")){
                List<Booking> bookingList = bookingRepository.findByUserId(userId);
                return bookingList.stream().map((Booking b) ->
                        new BookingListResponseDto(
                                b.getBookingId(),
                                b.getDate(),
                                b.getNoOfPerson(),
                                b.getActivity().getActivityName(),
                                b.getActivityId(),
                                b.getUser().getName())).collect(Collectors.toList());
            }else {
                throw new BadRequestException(HttpStatus.BAD_REQUEST, "Id doesn't represent user.");
            }
        }else{
            throw new BadRequestException(HttpStatus.BAD_REQUEST, "User is not Active.");
        }
    }

    @Override
    public Long getCountOfBooking() {
        return bookingRepository.countOfBookings();
    }

    @Override
    public List<BookingListResponseDto> getBookingsList() {
        List<Booking> bookingList = bookingRepository.findAll();
        return bookingList.stream().map((Booking b) ->
                new BookingListResponseDto(
                        b.getBookingId(),
                        b.getDate(),
                        b.getNoOfPerson(),
                        b.getActivity().getActivityName(),
                        b.getActivityId(),
                        b.getUser().getName()
                )).collect(Collectors.toList());
    }

    @Override
    public BookingDetailsDto getBookingDetails(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(() ->
                new NotFoundException(HttpStatus.NOT_FOUND, "Booking doesn't exists."));
        if(booking.getDeletedTimeStamp() == null){
            BookingDetailsDto bookingDetails = modelMapper.map(booking, BookingDetailsDto.class);
            bookingDetails.setActivityName(booking.getActivity().getActivityName());
            bookingDetails.setAboutActivity(booking.getActivity().getAboutActivity());
            bookingDetails.setShortDescription(booking.getActivity().getShortDescription());
            bookingDetails.setPropertyId(booking.getActivity().getPropertyId());
            bookingDetails.setLandOwnerId(booking.getActivity().getProperty().getUserId());
            bookingDetails.setLandOwnerName(booking.getActivity().getProperty().getUser().getName());
            bookingDetails.setLandOwnerEmail(booking.getActivity().getProperty().getUser().getEmail());
            bookingDetails.setLandOwnerPhoneNumber(booking.getActivity().getProperty().getUser().getPhoneNumber());
            List<Amenities> amenities = amenitiesRepository.amenityList(booking.getActivityId());
            List<ViewAmenitiesDto> amenitiesDtoList = amenities.stream().map((Amenities a) ->
                    new ViewAmenitiesDto(
                            a.getId(),
                            a.getIcon(),
                            a.getName()
                    )).collect(Collectors.toList());
            bookingDetails.setViewAmenityList(amenitiesDtoList);
            return bookingDetails;
        }else {
            throw new BadRequestException(HttpStatus.BAD_REQUEST, "Booking is not active");
        }
    }
}
