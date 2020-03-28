package com.github.simplesteps.grpc.calculator.server;

import com.proto.calculator.CalculatorServiceGrpc;
import com.proto.calculator.ComputeAverageRequest;
import com.proto.calculator.ComputeAverageResponse;
import com.proto.calculator.FindMaximumRequest;
import com.proto.calculator.FindMaximumResponse;
import com.proto.calculator.PrimeNumberDecompositionRequest;
import com.proto.calculator.PrimeNumberDecompositionResponse;
import com.proto.calculator.SquareRootRequest;
import com.proto.calculator.SquareRootResponse;
import com.proto.calculator.SumRequest;
import com.proto.calculator.SumResponse;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;

public class CalculatorServiceImpl extends CalculatorServiceGrpc.CalculatorServiceImplBase {

  @Override
  public void sum(SumRequest request, StreamObserver<SumResponse> responseObserver) {
    //super.sum(request, responseObserver);
    SumResponse sumResponse = SumResponse.newBuilder()
        .setSumResult(request.getFirstNumber() + request.getSecondNumber())
        .build();

    responseObserver.onNext(sumResponse);

    responseObserver.onCompleted();
  }

  @Override
  public void primeNumberDecomposition(PrimeNumberDecompositionRequest request,
      StreamObserver<PrimeNumberDecompositionResponse> responseObserver) {
    //super.primeNumberDecomposition(request, responseObserver);

    Long number = request.getNumber();
    Integer divisor = 2;
    while(number > 1) {
      if(number % divisor == 0) {
        number = number / divisor;
        responseObserver.onNext(PrimeNumberDecompositionResponse.newBuilder()
            .setPrimeFactor(divisor)
            .build());
      } else {
        divisor++;
      }
    }
    responseObserver.onCompleted();
  }

  @Override
  public StreamObserver<ComputeAverageRequest> computeAverage(
      StreamObserver<ComputeAverageResponse> responseObserver) {

    StreamObserver<ComputeAverageRequest> computeAverageRequestStreamObserver = new StreamObserver<ComputeAverageRequest>() {

      int sum = 0;
      int count = 0 ;
      @Override
      public void onNext(ComputeAverageRequest value) {
        sum += value.getNumber();
        count++;
      }

      @Override
      public void onError(Throwable t) {

      }

      @Override
      public void onCompleted() {
        double avg  = (double) sum / count ;
        responseObserver.onNext(ComputeAverageResponse.newBuilder()
            .setAverage(avg).build());

        responseObserver.onCompleted();

      }
    };
    return computeAverageRequestStreamObserver;
  }

  @Override
  public StreamObserver<FindMaximumRequest> findMaximum(
      StreamObserver<FindMaximumResponse> responseObserver) {
     return new StreamObserver<FindMaximumRequest>() {
       int currentmaximum = 0;
       @Override
       public void onNext(FindMaximumRequest value) {
         int currentNumber = value.getNumber();
         if(currentNumber > currentmaximum) {
           currentmaximum = currentNumber;
           responseObserver.onNext(FindMaximumResponse.newBuilder()
               .setMaximum(currentNumber).build());
         }
       }

       @Override
       public void onError(Throwable t) {
         responseObserver.onCompleted();
       }

       @Override
       public void onCompleted() {
         System.out.println("server done last max");
         responseObserver.onNext(FindMaximumResponse.newBuilder()
             .setMaximum(currentmaximum).build());
         responseObserver.onCompleted();
       }
     };
  }

  @Override
  public void squareRoot(SquareRootRequest request,
      StreamObserver<SquareRootResponse> responseObserver) {

    Integer number = request.getNumber();
    if(number > 0) {
      double root = Math.sqrt(number);
      responseObserver.onNext(SquareRootResponse.newBuilder()
          .setNumberRoot(root).build());
    } else {
      responseObserver.onError(Status.INVALID_ARGUMENT.withDescription("Number is not positive")
          .augmentDescription("number sent: "+number)
      .asRuntimeException());
    }
    responseObserver.onCompleted();
  }
}
