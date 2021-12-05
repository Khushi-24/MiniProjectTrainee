package com.example.MiniProjectApis.Dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PropertyDto {

    private Long propertyId;

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

    @NotNull(message = "Property distance can't be null or empty")
    private Long distance;

    private List<ImageDto> imageDtoList;
}
