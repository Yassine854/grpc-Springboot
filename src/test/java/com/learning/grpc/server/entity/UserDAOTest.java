package com.learning.grpc.server.entity;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import org.junit.Test;

public class UserDAOTest {

  @Test
  public void create() {
    UserDAO u = new UserDAO();

    User yassine = new User();
    yassine.setId(new Random().nextInt(1000000));
    yassine.setName("Yassine");
    yassine.setAge(24);
    u.create(yassine);

    User rayen = new User();
    rayen.setId(new Random().nextInt(1000000));
    rayen.setName("Rayen");
    rayen.setAge(24);
    u.create(rayen);

    User emna = new User();
    emna.setId(new Random().nextInt(1000000));
    emna.setName("Emna");
    emna.setAge(24);
    u.create(emna);

    User farah = new User();
    farah.setId(new Random().nextInt(1000000));
    farah.setName("Farah");
    farah.setAge(24);
    u.create(farah);
  }


//  @Test
//  public void getUser() {
//    UserDAO u = new UserDAO();
//
//    int randomId = new Random().nextInt(1000000);
//
//    User user = new User();
//    user.setId(randomId);
//    user.setName("Bejo");
//    user.setAge(new Random().nextInt(24));
//    u.create(user);
//
//    Optional userFound = u.getUser(randomId);
//
//    assertTrue(userFound.isPresent());
//  }

  @Test
  public void getAllUsers() {
    UserDAO u = new UserDAO();

    List<User> users = u.getAllUsers();

    assertFalse(users.isEmpty());
  }
}