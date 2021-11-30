package com.example.MiniProjectApis.Dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ViewPropertyDetailsResponseDto {

    private Long propertyId;

    private String propertyName;

    private String propertyAddress;

    private String propertyArea;

    private Long userId;

    private List<ViewImageResponseDto> propertyImageResponseDto;

}
