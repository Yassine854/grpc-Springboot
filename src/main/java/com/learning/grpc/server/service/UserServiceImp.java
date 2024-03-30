package com.learning.grpc.server.service;

import com.google.protobuf.Empty;
import com.learning.grpc.GeneralResponse;
import com.learning.grpc.UserObj;
import com.learning.grpc.UserRequest;
import com.learning.grpc.UserServiceGrpc.UserServiceImplBase;
import com.learning.grpc.UsersResponse;
import com.learning.grpc.server.entity.User;
import com.learning.grpc.server.entity.UserDAO;
import io.grpc.stub.StreamObserver;
import java.util.List;
import java.util.Optional;

public class UserServiceImp extends UserServiceImplBase {

  UserDAO userDao;

  public UserServiceImp() {
    this.userDao = new UserDAO();
  }

  @Override
  public void getAllUsers(Empty emptyReq, StreamObserver<UsersResponse> responseObserver) {
    List<User> users = userDao.getAllUsers();

    UsersResponse usersObj = UsersResponse.newBuilder()
        .addAllUsers(
            users.stream().map(
              u -> UserObj.newBuilder()
                  .setId(u.getId())
                  .setName(u.getName())
                  .setAge(u.getAge())
                  .build()
            ).toList()
        )
        .build();

    responseObserver.onNext(usersObj);
    responseObserver.onCompleted();
  }

  @Override
  public void getUser(UserRequest request, StreamObserver<UserObj> responseObserver) {
    int idToFind = request.getId();

    Optional user = userDao.getUser(idToFind);

    UserObj userObj;

    if(user.isPresent()) {
      System.out.println("Got user " + idToFind + " in db " + user.toString());

      User u = (User) user.get();

      userObj = UserObj.newBuilder()
          .setId(u.getId())
          .setName(u.getName())
          .setAge(u.getAge())
          .build();
    } else {
      userObj = UserObj.newBuilder()
          .setId(0)
          .setName("")
          .setAge(0)
          .build();
    }

    responseObserver.onNext(userObj);
    responseObserver.onCompleted();
  }

  @Override
  public void create(UserObj request, StreamObserver<GeneralResponse> responseObserver) {
    User user = new User();
    user.setId(request.getId());
    user.setName(request.getName());
    user.setAge(request.getAge());

    userDao.create(user);

    GeneralResponse resp = GeneralResponse.newBuilder()
        .setResponse("OK")
        .build();

    responseObserver.onNext(resp);
    responseObserver.onCompleted();
  }

  @Override
  public void update(UserObj request, StreamObserver<GeneralResponse> responseObserver) {

  }

  @Override
  public void delete(UserRequest request, StreamObserver<GeneralResponse> responseObserver) {

  }
}
