package com.github.simplesteps.grpc.calculator.client;

import com.proto.calculator.CalculatorServiceGrpc;
import com.proto.calculator.FindMaximumRequest;
import com.proto.calculator.FindMaximumResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class FindMaximumClient {

  public static void main(String[] args) throws InterruptedException {

    ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50052)
        .usePlaintext()
        .build();

    CalculatorServiceGrpc.CalculatorServiceStub asyncClient = CalculatorServiceGrpc.newStub(channel);
    CountDownLatch latch = new CountDownLatch(1);

    StreamObserver<FindMaximumRequest> findMaximumRequestStreamObserver = asyncClient.findMaximum(
        new StreamObserver<FindMaximumResponse>() {
          @Override
          public void onNext(FindMaximumResponse value) {
            System.out.println(" Got max from server "+ value.getMaximum());
          }

          @Override
          public void onError(Throwable t) {
            latch.countDown();
          }

          @Override
          public void onCompleted() {
            System.out.println("server is done sending messages");
            latch.countDown();
          }
        }
    );

    Arrays.asList(3,5,6,7,1,2,3,9,11,12,1,2,3)
        .forEach( num -> {
          System.out.println("sending number "+num);
          findMaximumRequestStreamObserver.onNext(FindMaximumRequest.newBuilder()
              .setNumber(num).build());
          try {
            Thread.sleep(100);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        });

    findMaximumRequestStreamObserver.onCompleted();
    latch.await(3, TimeUnit.SECONDS);
  }
}
