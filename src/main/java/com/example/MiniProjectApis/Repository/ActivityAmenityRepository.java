package com.example.MiniProjectApis.Repository;

import com.example.MiniProjectApis.Entities.ActivityAmenities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityAmenityRepository extends JpaRepository<ActivityAmenities, Long> {

    @Query(value = "SELECT * FROM `activity_amenities` where activity_id = ?1 and id = ?2 AND deleted_date is Null", nativeQuery = true)
    ActivityAmenities getByActivityIdAndAmenityId(Long activityId, Long amenityId);

    @Query(value = "SELECT * FROM `activity_amenities` WHERE id = ?1 and deleted_date is null", nativeQuery = true)
    List<ActivityAmenities> findByAmenityId(Long amenityId);
}
