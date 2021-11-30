package com.example.MiniProjectApis.Dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ApproveLandOwnerDto {

    @NotNull(message = "LandOwner Id can't be null or empty.")
    private Long id;

    @NotNull(message = "isApproved field can't be null or empty.")
    private Boolean isApproved;
}
