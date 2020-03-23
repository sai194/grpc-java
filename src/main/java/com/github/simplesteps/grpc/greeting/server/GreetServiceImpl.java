package com.github.simplesteps.grpc.greeting.server;

import com.proto.greet.GreetManyTimesRequest;
import com.proto.greet.GreetManyTimesResponse;
import com.proto.greet.GreetRequest;
import com.proto.greet.GreetResponse;
import com.proto.greet.GreetServiceGrpc;
import com.proto.greet.Greeting;
import com.proto.greet.LongGreetRequest;
import com.proto.greet.LongGreetResponse;
import io.grpc.stub.StreamObserver;
import java.text.MessageFormat;

public class GreetServiceImpl extends GreetServiceGrpc.GreetServiceImplBase {

  @Override
  public void greet(GreetRequest request, StreamObserver<GreetResponse> responseObserver) {
    //super.greet(request, responseObserver);
    Greeting greeting = request.getGreeting();
    String firstName = greeting.getFirstName();
    String lastName = greeting.getLastName();
    //create response
    String result = MessageFormat.format(" Hello {0}, {1}", firstName, lastName);
    GreetResponse response = GreetResponse.newBuilder()
        .setResult(result)
        .build();
    //send response
    responseObserver.onNext(response);
    //complete rpc call
    responseObserver.onCompleted();
  }

  @Override
  public void greetManyTimes(GreetManyTimesRequest request,
      StreamObserver<GreetManyTimesResponse> responseObserver) {
    //super.greetManyTimes(request, responseObserver);
    Greeting greeting = request.getGreeting();
    String firstName = greeting.getFirstName();
    String lastName = greeting.getLastName();
    try {
      for (int i = 0; i < 10; i++) {
        String result = MessageFormat.format(" Hello {0}, {1} : {2}", firstName, lastName, i);
        GreetManyTimesResponse response = GreetManyTimesResponse.newBuilder()
            .setResult(result)
            .build();
        responseObserver.onNext(response);

        Thread.sleep(1000L);
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      responseObserver.onCompleted();
    }
  }

  @Override
  public StreamObserver<LongGreetRequest> longGreet(
      StreamObserver<LongGreetResponse> responseObserver) {
    StreamObserver<LongGreetRequest> longGreetRequestStreamObserver = new StreamObserver<LongGreetRequest>() {
      String result = "";
      @Override
      public void onNext(LongGreetRequest value) {
        result += "Hello "+value.getGreeting().getFirstName()+"! ";
      }

      @Override
      public void onError(Throwable t) {

      }

      @Override
      public void onCompleted() {
        responseObserver.onNext(LongGreetResponse.newBuilder()
            .setResult(result)
            .build());
        responseObserver.onCompleted();
      }
    };

    return longGreetRequestStreamObserver;
  }
}
