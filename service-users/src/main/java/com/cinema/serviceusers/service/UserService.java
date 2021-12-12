package com.cinema.serviceusers.service;

import com.cinema.serviceusers.repo.model.Type;
import com.cinema.serviceusers.repo.model.User;

import java.util.List;

public interface UserService {
  List<User> fetchAllUsers();
  User fetchUserById(long id) throws IllegalArgumentException;
  long createUser(String name, Integer age, Type type);
  User addUser(User user);
  void updateUser(long id, String name, Integer age, Type type) throws IllegalArgumentException;
  void deleteUser(long id);
}
