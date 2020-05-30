package com.mapper;

import com.entities.ApplicationUser;
import com.input.Authentication;

public class ProfileMapper {

    public static ApplicationUser profileToEntity(Authentication input) {
        return ApplicationUser.builder()
                .userName(input.getUserName())
                .password(input.getPassword())
                .firstName(input.getFirstName())
                .lastName(input.getLastName())
                .build();
    }


}
