// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: calculator/calculator.proto

package com.proto.calculator;

public final class Calculator {
  private Calculator() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_greet_SumRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_greet_SumRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_greet_SumResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_greet_SumResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_greet_PrimeNumberDecompositionRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_greet_PrimeNumberDecompositionRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_greet_PrimeNumberDecompositionResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_greet_PrimeNumberDecompositionResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_greet_ComputeAverageRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_greet_ComputeAverageRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_greet_ComputeAverageResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_greet_ComputeAverageResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_greet_FindMaximumRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_greet_FindMaximumRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_greet_FindMaximumResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_greet_FindMaximumResponse_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\033calculator/calculator.proto\022\005greet\"9\n\n" +
      "SumRequest\022\024\n\014first_number\030\001 \001(\005\022\025\n\rseco" +
      "nd_number\030\002 \001(\005\"!\n\013SumResponse\022\022\n\nsum_re" +
      "sult\030\001 \001(\005\"1\n\037PrimeNumberDecompositionRe" +
      "quest\022\016\n\006number\030\001 \001(\003\"8\n PrimeNumberDeco" +
      "mpositionResponse\022\024\n\014prime_factor\030\001 \001(\003\"" +
      "\'\n\025ComputeAverageRequest\022\016\n\006number\030\001 \001(\005" +
      "\")\n\026ComputeAverageResponse\022\017\n\007average\030\001 " +
      "\001(\001\"$\n\022FindMaximumRequest\022\016\n\006number\030\001 \001(" +
      "\005\"&\n\023FindMaximumResponse\022\017\n\007maximum\030\001 \001(" +
      "\0052\317\002\n\021CalculatorService\022.\n\003Sum\022\021.greet.S" +
      "umRequest\032\022.greet.SumResponse\"\000\022m\n\030Prime" +
      "NumberDecomposition\022&.greet.PrimeNumberD" +
      "ecompositionRequest\032\'.greet.PrimeNumberD" +
      "ecompositionResponse0\001\022Q\n\016ComputeAverage" +
      "\022\034.greet.ComputeAverageRequest\032\035.greet.C" +
      "omputeAverageResponse\"\000(\001\022H\n\013FindMaximum" +
      "\022\031.greet.FindMaximumRequest\032\032.greet.Find" +
      "MaximumResponse(\0010\001B\030\n\024com.proto.calcula" +
      "torP\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_greet_SumRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_greet_SumRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_greet_SumRequest_descriptor,
        new java.lang.String[] { "FirstNumber", "SecondNumber", });
    internal_static_greet_SumResponse_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_greet_SumResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_greet_SumResponse_descriptor,
        new java.lang.String[] { "SumResult", });
    internal_static_greet_PrimeNumberDecompositionRequest_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_greet_PrimeNumberDecompositionRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_greet_PrimeNumberDecompositionRequest_descriptor,
        new java.lang.String[] { "Number", });
    internal_static_greet_PrimeNumberDecompositionResponse_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_greet_PrimeNumberDecompositionResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_greet_PrimeNumberDecompositionResponse_descriptor,
        new java.lang.String[] { "PrimeFactor", });
    internal_static_greet_ComputeAverageRequest_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_greet_ComputeAverageRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_greet_ComputeAverageRequest_descriptor,
        new java.lang.String[] { "Number", });
    internal_static_greet_ComputeAverageResponse_descriptor =
      getDescriptor().getMessageTypes().get(5);
    internal_static_greet_ComputeAverageResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_greet_ComputeAverageResponse_descriptor,
        new java.lang.String[] { "Average", });
    internal_static_greet_FindMaximumRequest_descriptor =
      getDescriptor().getMessageTypes().get(6);
    internal_static_greet_FindMaximumRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_greet_FindMaximumRequest_descriptor,
        new java.lang.String[] { "Number", });
    internal_static_greet_FindMaximumResponse_descriptor =
      getDescriptor().getMessageTypes().get(7);
    internal_static_greet_FindMaximumResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_greet_FindMaximumResponse_descriptor,
        new java.lang.String[] { "Maximum", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
