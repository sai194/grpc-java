package com.github.simplesteps.grpc.greeting.client;

import com.proto.greet.GreetServiceGrpc;
import com.proto.greet.GreetServiceGrpc.GreetServiceBlockingStub;
import com.proto.greet.GreetWithDeadLineRequest;
import com.proto.greet.GreetWithDeadLineResponse;
import com.proto.greet.Greeting;
import io.grpc.Deadline;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import java.util.concurrent.TimeUnit;

public class GreetWithDeadLineClient {

  public static void main(String[] args) {
    System.out.println("Hello gRPC Client!");
    ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
        .usePlaintext()
        .build();
    GreetServiceBlockingStub stub = GreetServiceGrpc.newBlockingStub(channel);
    try {
      GreetWithDeadLineResponse response = stub
          .withDeadline(Deadline.after(5000, TimeUnit.MILLISECONDS))
          .greetWithDeadLine(GreetWithDeadLineRequest.newBuilder()
              .setGreeting(Greeting.newBuilder().setFirstName("SAi").build()).build());
      System.out.println(response.getResult());
    }catch (StatusRuntimeException e) {
      if (e.getStatus() == Status.DEADLINE_EXCEEDED) {
        System.out.println("deadline exceeded!");
      }
      e.printStackTrace();
    }

    try {
      GreetWithDeadLineResponse response = stub
          .withDeadline(Deadline.after(200, TimeUnit.MILLISECONDS))
          .greetWithDeadLine(GreetWithDeadLineRequest.newBuilder()
              .setGreeting(Greeting.newBuilder().setFirstName("SAi").build()).build());
      System.out.println(response.getResult());
    }catch (StatusRuntimeException e) {
      if (e.getStatus() == Status.DEADLINE_EXCEEDED) {
        System.out.println("deadline exceeded!");
      }
      e.printStackTrace();
    }
  }
}
