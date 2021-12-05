package com.example.MiniProjectApis.Repository;

import com.example.MiniProjectApis.Entities.ActivityImages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityImageRepository extends JpaRepository<ActivityImages, Long> {

    @Query(value = "SELECT Count(*) FROM `activity_images` as p WHERE p.activity_id =?1 AND deleted_date is Null", nativeQuery = true)
    Long countByActivityId(Long activityId);

    @Query(value = "SELECT * FROM `activity_images` as p WHERE p.activity_id =?1 AND deleted_date is Null", nativeQuery = true)
    List<ActivityImages> findByActivityId(Long activityId);
}
