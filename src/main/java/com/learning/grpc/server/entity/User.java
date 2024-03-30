package com.learning.grpc.server.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
public class User {

  @Id
  @Column(name = "id", nullable = false)
  @Getter
  @Setter
  private int id;

  @Column(name = "name")
  @Getter
  @Setter
  private String name;

  @Column(name = "age")
  @Getter
  @Setter
  private int age;

  @Override
  public String toString() {
    return "User [userId=" + id + ", name=" + name + "]";
  }
}
