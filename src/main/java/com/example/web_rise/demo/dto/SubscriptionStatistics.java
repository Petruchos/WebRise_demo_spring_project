package com.example.web_rise.demo.dto;

import com.example.web_rise.demo.constants.SubscriptionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionStatistics {
    private SubscriptionType subscriptionType;

    private Long subscriptionCount;
}
