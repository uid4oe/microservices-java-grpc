package com.uid4oe.java.bff.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class AdviceDto {
    String user_id;
    String advice;
    LocalDateTime created_at;
}
