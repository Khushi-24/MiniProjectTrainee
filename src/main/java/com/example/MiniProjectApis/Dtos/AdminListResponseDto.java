package com.example.MiniProjectApis.Dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminListResponseDto {

    private Long id;

    private String name;

    private String email;

    public AdminListResponseDto(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
