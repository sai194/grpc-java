package com.github.simplesteps.grpc.calculator.client;

import com.proto.calculator.CalculatorServiceGrpc;
import com.proto.calculator.SquareRootRequest;
import com.proto.calculator.SquareRootResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

public class SquareRootClient {

  public static void main(String[] args) {
    ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50052)
        .usePlaintext()
        .build();

    CalculatorServiceGrpc.CalculatorServiceBlockingStub stub = CalculatorServiceGrpc.newBlockingStub(channel);

    int number = 10;

    try {
      SquareRootResponse response =  stub.squareRoot(SquareRootRequest.newBuilder().
          setNumber(number).build());
      System.out.println(response.getNumberRoot());
    } catch (StatusRuntimeException e) {
      e.printStackTrace();
    }
  }

}
