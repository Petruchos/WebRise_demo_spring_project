package com.example.web_rise.demo.utils.converter;

import com.example.web_rise.demo.dto.User;
import com.example.web_rise.demo.entity.UserEntity;

import java.util.stream.Collectors;

public class UserDTOConverter {

    public static User toDto(UserEntity entity){
        return new User(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getSubscriptions().stream().map(SubscriptionDTOConverter::toDto).collect(Collectors.toList())
        );
    }

}
