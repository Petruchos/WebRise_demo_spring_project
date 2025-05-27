package com.example.web_rise.demo.dto;

import com.example.web_rise.demo.constants.SubscriptionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddSubscriptionPayload {

    private SubscriptionType subscriptionType;

    private LocalDate startDate;

    private LocalDate renewDate;

    private LocalDate expirationDate;
}
