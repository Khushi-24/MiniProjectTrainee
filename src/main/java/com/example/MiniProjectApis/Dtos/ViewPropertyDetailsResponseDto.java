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

    private String imageIcon;

    private Long distance;

    private Long landOwnerId;

    private String landOwnerName;

    private String landOwnerEmail;

    private String landOwnerPhoneNumber;

    private String landOwnerAddress;

    private List<ViewImageResponseDto> propertyImageResponseDto;

}
