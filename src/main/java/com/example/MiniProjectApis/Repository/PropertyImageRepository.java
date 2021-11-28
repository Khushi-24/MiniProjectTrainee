package com.example.MiniProjectApis.Repository;

import com.example.MiniProjectApis.Entities.PropertyImages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyImageRepository extends JpaRepository<PropertyImages, Long> {
}
