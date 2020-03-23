package com.github.simplesteps.grpc.greeting.client;

import com.proto.greet.GreetRequest;
import com.proto.greet.GreetResponse;
import com.proto.greet.GreetServiceGrpc;
import com.proto.greet.GreetServiceGrpc.GreetServiceBlockingStub;
import com.proto.greet.Greeting;
import com.proto.greet.LongGreetRequest;
import com.proto.greet.LongGreetResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class LongGreetClient {

  public static void main(String[] args) throws IOException, InterruptedException {
    System.out.println("Hello gRPC Client!");
    ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
        .usePlaintext()
        .build();

    GreetServiceGrpc.GreetServiceStub asyncClient = GreetServiceGrpc.newStub(channel);
    CountDownLatch latch = new CountDownLatch(1);
    StreamObserver<LongGreetRequest> longGreetRequestStreamObserver = asyncClient
        .longGreet(new StreamObserver<LongGreetResponse>() {
          @Override

          public void onNext(LongGreetResponse value) {
            System.out.println("Received response from server");
            System.out.println(value.getResult());
          }

          @Override
          public void onError(Throwable t) {

          }

          @Override
          public void onCompleted() {
            System.out.println("server sent something");
            latch.countDown();

          }
        });
    longGreetRequestStreamObserver.onNext(LongGreetRequest.newBuilder()
        .setGreeting(Greeting.newBuilder()
        .setFirstName("sai")
        .build())
        .build());
    longGreetRequestStreamObserver.onNext(LongGreetRequest.newBuilder()
        .setGreeting(Greeting.newBuilder()
            .setFirstName("yeluri")
            .build())
        .build());
    longGreetRequestStreamObserver.onNext(LongGreetRequest.newBuilder()
        .setGreeting(Greeting.newBuilder()
            .setFirstName("ikshvak")
            .build())
        .build());
    longGreetRequestStreamObserver.onCompleted();
    latch.await(3L, TimeUnit.SECONDS);
    System.out.println("Shutting down channel!");
    channel.shutdown();
  }

}
