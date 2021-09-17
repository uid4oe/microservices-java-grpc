package com.uid4oe.java.bff;

import advicepb.Advice;
import advicepb.AdviceServiceGrpc;
import com.uid4oe.java.bff.dto.AdviceDto;
import com.uid4oe.java.bff.dto.UserDetailsDto;
import com.uid4oe.java.bff.dto.UserDto;
import com.uid4oe.java.bff.dto.UserSummaryDto;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;
import userpb.User;
import userpb.UserServiceGrpc;

import java.time.Instant;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BffBusiness {

    @GrpcClient("user")
    private UserServiceGrpc.UserServiceBlockingStub userClient;

    @GrpcClient("advice")
    private AdviceServiceGrpc.AdviceServiceBlockingStub adviceClient;

    List<UserSummaryDto> getUsers() {
        var response = userClient.getUsers(User.GetUsersRequest.newBuilder().build());
        return response.getUsersList().stream().map(
                item -> UserSummaryDto.builder()
                        .id(item.getId())
                        .name(item.getName())
                        .age(item.getAge())
                        .greeting(item.getGreeting())
                        .build()).collect(Collectors.toList());
    }

    UserDetailsDto getUserDetails(String id) {
        var response = userClient.getUserDetails(
                User.GetUserDetailsRequest.newBuilder()
                        .setId(id).build()
        );
        var adviceResponse = adviceClient.getAdvice(Advice.
                GetUserAdviceRequest.newBuilder().setUserId(id).build());
        return UserDetailsDto.builder()
                .salary(response.getSalary())
                .power(response.getPower())
                .advice(adviceResponse.getAdvice())
                .created_at(Instant
                        .ofEpochSecond(adviceResponse.getCreatedAt().getSeconds(),
                                adviceResponse.getCreatedAt().getNanos())
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime()).build();

    }


    String createUser(UserDto user) {
        var createdUserId = userClient.createUpdateUser(UserDto.toCreateUpdateRequest(user, userpb.User.Operation.CREATE));
        adviceClient.createUpdateAdvice(
                Advice.CreateUpdateAdviceRequest.newBuilder()
                        .setUserId(createdUserId.getId())
                        .setAdvice(user.getAdvice()).build()
        );
        return user.getId();
    }

    String updateUser(String id, UserDto user) {
        user.setId(id);
        userClient.createUpdateUser(UserDto.toCreateUpdateRequest(user, User.Operation.UPDATE));
        return user.getId();
    }


    String updateAdvice(AdviceDto advice) {
        adviceClient.createUpdateAdvice(
                Advice.CreateUpdateAdviceRequest.newBuilder()
                        .setUserId(advice.getUser_id())
                        .setAdvice(advice.getAdvice()).build()
        );
        return "OK";
    }

}

