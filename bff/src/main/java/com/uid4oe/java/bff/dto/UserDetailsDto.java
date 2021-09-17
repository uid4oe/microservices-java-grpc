package com.uid4oe.java.bff.dto;

import lombok.Builder;
import lombok.Getter;
import org.apache.tomcat.jni.Local;

import java.time.LocalDateTime;

@Builder
@Getter
public class UserDetailsDto {
    Integer salary;
    String power;
    String advice;
    LocalDateTime created_at;
}
