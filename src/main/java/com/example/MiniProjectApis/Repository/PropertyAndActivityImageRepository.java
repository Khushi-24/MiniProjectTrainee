package com.example.MiniProjectApis.Repository;

import com.example.MiniProjectApis.Entities.PropertyAndActivityImages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyAndActivityImageRepository extends JpaRepository<PropertyAndActivityImages, Long> {

    @Query(value = "SELECT Count(*) FROM `property_and_activity_images` as p WHERE p.property_id =?1 AND deleted_date is Null", nativeQuery = true)
    Long countByPropertyId(Long propertyId);

    @Query(value = "SELECT * FROM `property_and_activity_images` as p WHERE p.property_id =?1 AND deleted_date is Null", nativeQuery = true)
    List<PropertyAndActivityImages> findByPropertyId(Long propertyId);

    @Query(value = "SELECT * FROM `property_and_activity_images` as p WHERE p.activity_id =?1 AND deleted_date is Null", nativeQuery = true)
    List<PropertyAndActivityImages> findByActivityId(Long activityId);
}
