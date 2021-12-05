package com.example.MiniProjectApis.Dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AmenityListDto {

    private String name;
    private String icon;

    public AmenityListDto(String name, String icon) {

        this.name = name;
        this.icon = icon;
    }
}
