package com.uid4oe.java.bff.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import userpb.User;

import java.util.Objects;

@Builder
@Getter
@Setter
public class UserDto {
    String id;
    String name;
    Integer age;
    String greeting;
    Integer salary;
    String power;
    String advice;

    public static User.CreateUpdateUserRequest toCreateUpdateRequest(UserDto user, User.Operation operation) {
        return User.CreateUpdateUserRequest.newBuilder()
                .setOperation(operation)
                .setId(Objects.nonNull(user.id) ? user.id : "")
                .setName(user.name)
                .setAge(user.age)
                .setSalary(user.salary)
                .setGreeting(user.greeting)
                .setPower(user.power)
                .build();
    }
}
