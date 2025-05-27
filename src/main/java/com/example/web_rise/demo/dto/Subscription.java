package com.example.web_rise.demo.dto;

import com.example.web_rise.demo.constants.SubscriptionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Subscription {

    private Long id;

    private Date createDate;

    private Date updateDate;

    private LocalDate startDate;

    private LocalDate renewDate;

    private LocalDate expirationDate;

    private SubscriptionType subscriptionType;

}


