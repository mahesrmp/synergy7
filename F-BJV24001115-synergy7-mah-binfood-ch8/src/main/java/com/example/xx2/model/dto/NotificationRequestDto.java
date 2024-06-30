package com.example.xx2.model.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NotificationRequestDto {
    private String title;
    private String body;
    private String topic;
    private String token;
}
