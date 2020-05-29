package com.mapper;

import com.entities.ApplicationUser;
import com.input.Authentication;

public class ProfileMapper {

    public static ApplicationUser profileToEntity(Authentication input) {
        return ApplicationUser.builder()
                .username(input.getUsername())
                .password(input.getPassword())
                .firstName(input.getFirstName())
                .lastName(input.getLastName())
                .build();
    }

    public static Authentication inputToEntity(ApplicationUser entity) {
        return Authentication.builder()
                .username(entity.getUsername())
                .password(entity.getPassword())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .build();
    }
}
