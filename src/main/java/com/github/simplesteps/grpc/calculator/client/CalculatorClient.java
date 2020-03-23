package com.github.simplesteps.grpc.calculator.client;

import com.proto.calculator.CalculatorServiceGrpc;
import com.proto.calculator.SumRequest;
import com.proto.calculator.SumResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class CalculatorClient {

  public static void main(String[] args) {

    ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50052)
        .usePlaintext()
        .build();

    CalculatorServiceGrpc.CalculatorServiceBlockingStub stub = CalculatorServiceGrpc.newBlockingStub(channel);

    SumRequest sumRequest = SumRequest.newBuilder()
        .setFirstNumber(10)
        .setSecondNumber(30)
        .build();

    SumResponse sumResponse = stub.sum(sumRequest);
    System.out.println(sumResponse.getSumResult());
    channel.shutdown();
  }
}
