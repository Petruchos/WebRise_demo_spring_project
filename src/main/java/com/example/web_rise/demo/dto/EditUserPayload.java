package com.example.web_rise.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditUserPayload {
    @Nullable
    private String firstName;

    @Nullable
    private String lastName;

    @Nullable
    private String email;
}
