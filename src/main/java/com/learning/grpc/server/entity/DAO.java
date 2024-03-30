package com.learning.grpc.server.entity;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {

  List<T> getAllUsers();

  Optional<T> getUser(int id);

  void create(T t);

  void update(T t);

  void delete(T t);
}
