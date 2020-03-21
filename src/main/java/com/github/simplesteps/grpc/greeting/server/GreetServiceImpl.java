package com.github.simplesteps.grpc.greeting.server;

import com.proto.greet.GreetRequest;
import com.proto.greet.GreetResponse;
import com.proto.greet.GreetServiceGrpc;
import com.proto.greet.Greeting;
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
}
