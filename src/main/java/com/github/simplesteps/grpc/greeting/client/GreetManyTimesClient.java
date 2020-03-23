package com.github.simplesteps.grpc.greeting.client;

import com.proto.greet.GreetManyTimesRequest;
import com.proto.greet.GreetManyTimesResponse;
import com.proto.greet.GreetRequest;
import com.proto.greet.GreetResponse;
import com.proto.greet.GreetServiceGrpc;
import com.proto.greet.GreetServiceGrpc.GreetServiceBlockingStub;
import com.proto.greet.Greeting;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import java.io.IOException;
import java.util.Iterator;

public class GreetManyTimesClient {
  public static void main(String[] args) throws IOException, InterruptedException {
    System.out.println("Hello gRPC Client!");
    ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
        .usePlaintext()
        .build();
    GreetServiceBlockingStub greetManyTimesClient = GreetServiceGrpc.newBlockingStub(channel);
    Greeting greeting = Greeting.newBuilder()
        .setFirstName("Sai")
        .setLastName("Yeluri")
        .build();
    GreetManyTimesRequest greetRequest = GreetManyTimesRequest.newBuilder()
        .setGreeting(greeting)
        .build();
    Iterator<GreetManyTimesResponse> greetManyTimesResponses = greetManyTimesClient.greetManyTimes(greetRequest);
    greetManyTimesResponses.forEachRemaining( greetManyTimesResponse -> {
      System.out.println(greetManyTimesResponse.getResult());
    });
    System.out.println("Shutting down channel!");
    channel.shutdown();
  }
}
