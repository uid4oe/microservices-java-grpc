package com.uid4oe.java.advice;

import io.grpc.Status;
import net.devh.boot.grpc.server.advice.GrpcAdvice;
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler;

@GrpcAdvice
public class AdviceGrpcExceptionHandler {

    @GrpcExceptionHandler(Exception.class)
    Status handleException(Exception e) {
        return Status.INTERNAL.withDescription(e.getMessage()).withCause(e);
    }
}
