package com.example.MiniProjectApis.Dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ViewAmenitiesDto {

    private Long id;

    private String name;

    private String icon;

    public ViewAmenitiesDto(Long id, String icon, String name) {

        this.id = id;
        this.icon = icon;
        this.name = name;
    }
}
