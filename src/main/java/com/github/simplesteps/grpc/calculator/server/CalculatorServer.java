package com.github.simplesteps.grpc.calculator.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.protobuf.services.ProtoReflectionService;
import java.io.IOException;

public class CalculatorServer {

  public static void main(String[] args) throws IOException, InterruptedException {
    System.out.println("Hello gRPC!");

    Server server = ServerBuilder.forPort(50052)
        .addService(new CalculatorServiceImpl())
        .addService(ProtoReflectionService.newInstance())
        .build();

    server.start();
    Runtime.getRuntime()
        .addShutdownHook(new Thread(() -> {
          System.out.println("Received shutdown request!");
          server.shutdown();
          System.out.println("Successfully stopped server!");
        }));

    server.awaitTermination();
  }
}
