package com.github.simplesteps.grpc.calculator.client;

import com.proto.calculator.CalculatorServiceGrpc;
import com.proto.calculator.PrimeNumberDecompositionRequest;
import com.proto.calculator.PrimeNumberDecompositionResponse;
import com.proto.calculator.SumRequest;
import com.proto.calculator.SumResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import java.util.Iterator;

public class PrimeDecompositionClient {

  public static void main(String[] args) {

    ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50052)
        .usePlaintext()
        .build();

    CalculatorServiceGrpc.CalculatorServiceBlockingStub stub = CalculatorServiceGrpc
        .newBlockingStub(channel);

    PrimeNumberDecompositionRequest primeNumberDecompositionRequest = PrimeNumberDecompositionRequest
        .newBuilder()
        .setNumber(1234567898)
        .build();

    Iterator<PrimeNumberDecompositionResponse> primeNumberDecompositionResponses = stub
        .primeNumberDecomposition(primeNumberDecompositionRequest);
    primeNumberDecompositionResponses.forEachRemaining(primeNumberDecompositionResponse -> {
      System.out.println(primeNumberDecompositionResponse.getPrimeFactor());
    });
    channel.shutdown();
  }
}
