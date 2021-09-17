package com.uid4oe.java.user;


import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import userpb.User;

@Data
@Builder
@Document("user_java")
public class UserModel {
    @Id
    String id;
    String name;
    Integer age;
    String greeting;
    Integer salary;
    String power;

    static User.GetUserResponse modelToGetUserResponse(UserModel u) {
        return User.GetUserResponse.newBuilder()
                .setId(u.id)
                .setName(u.name)
                .setAge(u.age)
                .setGreeting(u.greeting)
                .build();
    }

    static User.GetUserDetailsResponse modelToGetUserDetailsResponse(UserModel u) {
        return User.GetUserDetailsResponse.newBuilder()
                .setSalary(u.salary)
                .setPower(u.power)
                .build();
    }
}
