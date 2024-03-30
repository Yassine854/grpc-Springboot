package com.learning.grpc.client;

import com.google.protobuf.Empty;
import com.learning.grpc.UserObj;
import com.learning.grpc.UserServiceGrpc;
import com.learning.grpc.UserServiceGrpc.UserServiceBlockingStub;
import com.learning.grpc.UsersResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class GrpcClient {

  public static void main(String[] args) {
    SpringApplication.run(GrpcClient.class, args);

    // Run gRPC client after Spring Boot application has started
    runGrpcClient();
  }

  private static void runGrpcClient() {
    ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8798)
            .usePlaintext()
            .build();

    UserServiceBlockingStub stub = UserServiceGrpc.newBlockingStub(channel);

    UsersResponse resp = stub.getAllUsers(Empty.getDefaultInstance());
    List<UserObj> users = resp.getUsersList();

    for (UserObj userObj : users) {
      String userInfo = new StringBuilder()
              .append("User: \n")
              .append("ID = " + userObj.getId() + "\n")
              .append("Name = " + userObj.getName() + "\n")
              .append("Age = " + userObj.getAge() + "\n")
              .toString();

      System.out.println(userInfo);
    }

    System.out.println("Total: " + users.size() + " users");

    channel.shutdown();
  }
}
