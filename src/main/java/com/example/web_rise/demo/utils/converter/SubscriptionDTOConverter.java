package com.example.web_rise.demo.utils.converter;

import com.example.web_rise.demo.dto.Subscription;
import com.example.web_rise.demo.entity.SubscriptionEntity;

public class SubscriptionDTOConverter {
    public static Subscription toDto(SubscriptionEntity entity){
        return new Subscription(
                entity.getId(),
                entity.getCreateDate(),
                entity.getUpdateDate(),
                entity.getStartDate(),
                entity.getRenewDate(),
                entity.getExpirationDate(),
                entity.getSubscriptionType()
        );
    }
}
