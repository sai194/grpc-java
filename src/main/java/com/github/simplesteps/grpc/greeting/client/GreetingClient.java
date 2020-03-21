package com.github.simplesteps.grpc.greeting.client;

import com.proto.greet.GreetRequest;
import com.proto.greet.GreetResponse;
import com.proto.greet.GreetServiceGrpc;
import com.proto.greet.GreetServiceGrpc.GreetServiceBlockingStub;
import com.proto.greet.Greeting;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import java.io.IOException;

public class GreetingClient {

  public static void main(String[] args) throws IOException, InterruptedException {
    System.out.println("Hello gRPC Client!");
    ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
        .usePlaintext()
        .build();
    GreetServiceBlockingStub greetClient = GreetServiceGrpc.newBlockingStub(channel);
    Greeting greeting = Greeting.newBuilder()
        .setFirstName("Sai")
        .setLastName("Yeluri")
        .build();
      GreetRequest greetRequest = GreetRequest.newBuilder()
          .setGreeting(greeting)
          .build();
      GreetResponse greetResponse = greetClient.greet(greetRequest);
      System.out.println(greetResponse.getResult());
      System.out.println("Shutting down channel!");
    channel.shutdown();
  }
}
