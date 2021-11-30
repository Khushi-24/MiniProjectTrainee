package com.example.MiniProjectApis.Dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class PropertyDto {

    @NotNull(message = "Property name can't be null or empty")
    @NotEmpty(message = "Property name can't be null or empty")
    private String propertyName;

    @NotNull(message = "Property address can't be null or empty")
    @NotEmpty(message = "Property address can't be null or empty")
    private String propertyAddress;

    @NotNull(message = "Property area can't be null or empty")
    @NotEmpty(message = "Property area can't be null or empty")
    private String propertyArea;

    @NotNull(message = "UserId can't be null or empty")
    private Long userId;

//    private Set<PropertyAndActivityImages> propertyImagesSet = new HashSet<>();
    private List<ImageDto> propertyImageDto;
}
