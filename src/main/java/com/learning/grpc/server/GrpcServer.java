package com.learning.grpc.server;

import com.learning.grpc.server.service.UserServiceImp;
import io.grpc.Server;
import io.grpc.ServerBuilder;

public class GrpcServer {
  public static void main(String[] args) {
    Server server = ServerBuilder
        .forPort(8798)
        .addService(new UserServiceImp()).build();

    try {
      server.start();
      server.awaitTermination();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
