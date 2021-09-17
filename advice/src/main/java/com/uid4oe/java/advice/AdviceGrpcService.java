package com.uid4oe.java.advice;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import advicepb.Advice;
import advicepb.AdviceServiceGrpc;

import java.time.LocalDateTime;

@GrpcService
public class AdviceGrpcService extends AdviceServiceGrpc.AdviceServiceImplBase {

    AdviceBusiness business;

    @Autowired
    public AdviceGrpcService(AdviceBusiness business) {
        this.business = business;
    }

    @Override
    public void getAdvice(Advice.GetUserAdviceRequest request, StreamObserver<Advice.GetUserAdviceResponse> responseObserver) {
        var adviceModel = business.getAdvice(request.getUserId());
        var response = Advice.GetUserAdviceResponse.newBuilder()
                .setAdvice(adviceModel.getAdvice())
                .setCreatedAt(AdviceModel.localDateToTimestamp(adviceModel.getCreated_at())).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void createUpdateAdvice(Advice.CreateUpdateAdviceRequest request, StreamObserver<Advice.CreateUpdateAdviceResponse> responseObserver) {
        business.createUpdateAdvice(
                AdviceModel.builder()
                        .user_id(request.getUserId())
                        .advice(request.getAdvice())
                        .created_at(LocalDateTime.now())
                        .build()
        );
        responseObserver.onNext(Advice.CreateUpdateAdviceResponse.newBuilder().build());
        responseObserver.onCompleted();
    }
}
