package com.api.restfulgym.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationsDTO {
    private int id;
    private String message;
    private ZonedDateTime createdAt;
}
