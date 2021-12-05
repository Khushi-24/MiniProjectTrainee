package com.example.MiniProjectApis.Dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PropertyListResponseDto {

    private Long propertyId;
    private String propertyName;
    private String propertyAddress;
    private String propertyArea;
    private Long userId;
    private Long distance;
    private String name;
    private List<ListOfActivityDto> list;

    public PropertyListResponseDto(Long propertyId, String propertyName, String propertyArea, String propertyAddress, Long userId,Long distance, String name) {

        this.propertyId = propertyId;
        this.propertyName = propertyName;
        this.propertyArea = propertyArea;
        this.propertyAddress = propertyAddress;
        this.userId = userId;
        this.distance = distance;
        this.name = name;
    }
}
