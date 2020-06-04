package com.mapper;

import com.entities.ApplicationUser;
import com.entities.UserEntity;
import com.model.User;


public class UserMapper {

    public static User entityToUser(UserEntity entity) {
        return User.builder()
                .email(entity.getEmail())
                .build();
    }

    public static UserEntity userToEntity(User user) {
        return UserEntity.builder()
                .email(user.getEmail())
                .build();
    }

    public static User accountToUser(ApplicationUser appU)
    {
        return User.builder()
                .email(appU.getUserName())
                .build();
    }


}
