package com.example.MiniProjectApis.Dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewImageResponseDto {

    private Long imageId;

    private String imageName;

    public ViewImageResponseDto(Long imageId, String imageName) {
        this.imageId = imageId;
        this.imageName = imageName;
    }
}
