package com.example.web_rise.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserPayload {
    private String firstName;
    private String lastName;
    private String email;
}
