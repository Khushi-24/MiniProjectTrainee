package com.example.MiniProjectApis.Repository;

import com.example.MiniProjectApis.Entities.Amenities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AmenitiesRepository extends JpaRepository<Amenities, Long> {

    @Query(value = "SELECT a.id, a.name, a.icon, a.created_date, a.deleted_date FROM `amenities` as a INNER JOIN activity_amenities AS aa ON a.id = aa.id where aa.activity_id = ?1 And a.deleted_date is null", nativeQuery = true)
    List<Amenities> amenityList(Long activityId);

    @Query(value = "SELECT COUNT(*) FROM `amenities` WHERE deleted_date IS Null", nativeQuery = true)
    Long countOfAmenities();
}
