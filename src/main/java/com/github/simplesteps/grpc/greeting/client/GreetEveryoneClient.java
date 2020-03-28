package com.github.simplesteps.grpc.greeting.client;

import com.proto.greet.GreetEveryoneRequest;
import com.proto.greet.GreetEveryoneResponse;
import com.proto.greet.GreetServiceGrpc;
import com.proto.greet.Greeting;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class GreetEveryoneClient {

  public static void main(String[] args) throws InterruptedException {
    System.out.println("Hello gRPC Client!");
    ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
        .usePlaintext()
        .build();

    GreetServiceGrpc.GreetServiceStub asyncClient = GreetServiceGrpc.newStub(channel);
    CountDownLatch latch = new CountDownLatch(1);

    StreamObserver<GreetEveryoneRequest> requestStreamObserver = asyncClient.greetEveryone(
        new StreamObserver<GreetEveryoneResponse>() {
          @Override
          public void onNext(GreetEveryoneResponse value) {
            System.out.println("Response from server "+value.getResult());
          }

          @Override
          public void onError(Throwable t) {
            latch.countDown();
          }

          @Override
          public void onCompleted() {
            System.out.println("Server is done sending data");
            latch.countDown();
          }
        }
    );
    Arrays.asList("Sai", "Yeluri","Ikshvak", "Satya").forEach(
        name -> {
          System.out.println("Sending: "+name);
          requestStreamObserver.onNext(GreetEveryoneRequest.newBuilder()
              .setGreeting(Greeting.newBuilder().setFirstName(name).build())
              .build());
          try {
            Thread.sleep(200);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
    );
    requestStreamObserver.onCompleted();
    latch.await(3, TimeUnit.SECONDS);
  }
}
