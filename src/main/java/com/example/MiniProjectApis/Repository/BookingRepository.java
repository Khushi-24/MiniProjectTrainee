package com.example.MiniProjectApis.Repository;

import com.example.MiniProjectApis.Entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query(value = "SELECT * FROM `booking` WHERE user_id =?1 and deleted_date IS Null", nativeQuery = true)
    List<Booking> findByUserId(Long userId);

    @Query(value = "SELECT COUNT(*) FROM `booking` WHERE deleted_date IS Null", nativeQuery = true)
    Long countOfBookings();
}
