package com.example.MiniProjectApis.Repository;

import com.example.MiniProjectApis.Entities.PropertyImages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyImageRepository extends JpaRepository<PropertyImages, Long> {

    @Query(value = "SELECT * FROM `property_images` as p WHERE p.property_id =?1 AND deleted_date is Null", nativeQuery = true)
    List<PropertyImages> findByPropertyId(Long propertyId);

    @Query(value = "SELECT Count(*) FROM `property_images` as p WHERE p.property_id =?1 AND deleted_date is Null", nativeQuery = true)
    Long countByPropertyId(Long propertyId);
}
