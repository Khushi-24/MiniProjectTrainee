package com.example.MiniProjectApis.Repository;

import com.example.MiniProjectApis.Entities.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {

    @Query(value = "SELECT * FROM `activity` WHERE activity.property_id =?1 and activity.deleted_date IS Null", nativeQuery = true)
    List<Activity> findByPropertyId(Long propertyId);

    @Query(value = "SELECT COUNT(*) FROM `activity` WHERE deleted_date IS Null", nativeQuery = true)
    Long activities();

    @Query(value = "SELECT * FROM `activity` where deleted_date is Null", nativeQuery = true)
    List<Activity> activityList();

    @Query(value = "SELECT * FROM `activity` where property_id =?1 And deleted_date is Null", nativeQuery = true)
    List<Activity> activityListByPropertyId(Long propertyId);

    List<Activity> findAllByPropertyId(Long propertyId);
}
