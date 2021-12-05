package com.example.MiniProjectApis.Dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LandOwnerList {

    private Long id;
    private String name;
    private String email;
    private String countryCode;
    private String phoneNumber;

    public LandOwnerList(Long id, String name, String email, String countryCode, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.countryCode = countryCode;
        this.phoneNumber = phoneNumber;
    }
}
