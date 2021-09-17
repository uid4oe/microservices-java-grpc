package com.uid4oe.java.user;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import userpb.User;
import userpb.UserServiceGrpc;
import org.bson.types.ObjectId;

import java.util.stream.Collectors;

@GrpcService
public class UserGrpcService extends UserServiceGrpc.UserServiceImplBase {

    UserBusiness business;

    @Autowired
    public UserGrpcService(UserBusiness business) {
        this.business = business;
    }

    @Override
    public void getUserDetails(User.GetUserDetailsRequest request, StreamObserver<User.GetUserDetailsResponse> responseObserver) {
        var details = business.getUserDetails(request.getId());
        responseObserver.onNext(UserModel.modelToGetUserDetailsResponse(details));
        responseObserver.onCompleted();
    }

    @Override
    public void getUsers(User.GetUsersRequest request, StreamObserver<User.GetUsersResponse> responseObserver) {
        var users = business.getUsers();
        var response = User.GetUsersResponse.newBuilder()
                .addAllUsers(users.stream()
                        .map(UserModel::modelToGetUserResponse)
                        .collect(Collectors.toList())).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }


    @Override
    public void createUpdateUser(User.CreateUpdateUserRequest request, StreamObserver<User.CreateUpdateUserResponse> responseObserver) {

        var uid = request.getId();

        if (request.getOperation().equals(User.Operation.CREATE)) {
            uid = new ObjectId().toHexString();
        }

        business.createUpdateUser(
                UserModel.builder()
                        .id(uid)
                        .name(request.getName())
                        .greeting(request.getGreeting())
                        .age(request.getAge())
                        .salary(request.getSalary())
                        .power(request.getPower())
                        .build()
        );
        responseObserver.onNext(User.CreateUpdateUserResponse.newBuilder().setId(uid).build());
        responseObserver.onCompleted();
    }
}
