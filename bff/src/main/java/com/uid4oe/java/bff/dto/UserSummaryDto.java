package com.uid4oe.java.bff.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserSummaryDto {
    String id;
    String name;
    Integer age;
    String greeting;
}
