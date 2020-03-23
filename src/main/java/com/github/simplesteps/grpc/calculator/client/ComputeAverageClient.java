package com.github.simplesteps.grpc.calculator.client;

import com.proto.calculator.CalculatorServiceGrpc;
import com.proto.calculator.ComputeAverageRequest;
import com.proto.calculator.ComputeAverageResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class ComputeAverageClient {

  public static void main(String[] args) throws InterruptedException {
    ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50052)
        .usePlaintext()
        .build();

    CalculatorServiceGrpc.CalculatorServiceStub asyncClient = CalculatorServiceGrpc.newStub(channel);
    CountDownLatch latch = new CountDownLatch(1);
    StreamObserver<ComputeAverageRequest> computeAverageRequestStreamObserver = asyncClient
        .computeAverage(new StreamObserver<ComputeAverageResponse>() {
          @Override
          public void onNext(ComputeAverageResponse value) {
            System.out.println("Received response from server");
            System.out.println(value.getAverage());
          }

          @Override
          public void onError(Throwable t) {

          }

          @Override
          public void onCompleted() {
            System.out.println("Server completed sending data");
            latch.countDown();
          }
        });

    computeAverageRequestStreamObserver.onNext(ComputeAverageRequest.newBuilder()
        .setNumber(10).build());
    computeAverageRequestStreamObserver.onNext(ComputeAverageRequest.newBuilder()
        .setNumber(20).build());
    computeAverageRequestStreamObserver.onNext(ComputeAverageRequest.newBuilder()
        .setNumber(30).build());
    computeAverageRequestStreamObserver.onCompleted();
    latch.await(3L, TimeUnit.SECONDS);
    channel.shutdown();
  }
}
