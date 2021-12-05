package com.example.MiniProjectApis.Repository;

import com.example.MiniProjectApis.Entities.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {

    @Query(value = "SELECT * FROM `property` WHERE deleted_date IS null", nativeQuery = true)
    List<Property> getPropertyList();

    @Query(value = "SELECT COUNT(*) FROM `property` WHERE deleted_date IS Null", nativeQuery = true)
    Long properties();

    @Query(value = "SELECT * FROM `property` WHERE distance BETWEEN ?1 And ?2 ORDER BY `property`.`distance` ASC", nativeQuery = true)
    List<Property> getPropertyListByDistance(Long from, Long to);
}
