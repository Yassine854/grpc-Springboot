package com.learning.grpc.server.entity;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
public class UserDAO implements DAO {

  EntityManagerFactory entityManagerFactory;
  EntityManager entityManager;

  public UserDAO() {
    entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
    entityManager = entityManagerFactory.createEntityManager();
  }

  @Override
  public List<User> getAllUsers() {
    System.out.println("Retrieving all users in DB");

    @SuppressWarnings("unchecked")
    List<User> users = entityManager.createQuery("SELECT u FROM User u").getResultList();

    return users;
  }

  @Override
  public Optional getUser(int id) {
    System.out.println("Retrieving user from DB");

    User user = entityManager.find(User.class, id);

    return Optional.ofNullable(user);
  }

  @Override
  public void create(Object o) {
    User user = (User) o;

    System.out.println("Starting Transaction");
    entityManager.getTransaction().begin();

    System.out.println("Saving User to Database");

    entityManager.persist(user);
    entityManager.getTransaction().commit();
  }

  @Override
  public void update(Object o) {

  }

  @Override
  public void delete(Object o) {

  }
}
