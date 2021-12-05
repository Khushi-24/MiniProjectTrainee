package com.example.MiniProjectApis.Dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserListResponseDto {

    private Long id;
    private String name;
    private String email;
    private String countryCode;
    private String phoneNumber;
    private String profilePicture;

    public UserListResponseDto(Long id, String name, String email, String countryCode, String phoneNumber, String profilePicture) {

        this.id = id;
        this.name = name;
        this.email = email;
        this.countryCode = countryCode;
        this.phoneNumber = phoneNumber;
        this.profilePicture = profilePicture;
    }
}
