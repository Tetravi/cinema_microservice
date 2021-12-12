package com.cinema.serviceusers.service.impl;

import com.cinema.serviceusers.repo.model.Type;
import com.cinema.serviceusers.repo.model.User;
import com.cinema.serviceusers.service.UserService;
import com.cinema.serviceusers.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

  private final UserRepo userRepo;

  public List<User> fetchAllUsers() {
    return userRepo.findAll();
  }

  public User fetchUserById(long id) throws IllegalArgumentException {
    final Optional<User> maybeUser = userRepo.findById(id);

    if (maybeUser.isPresent())
      return maybeUser.get();
    else
      throw new IllegalArgumentException("Invalid user ID");
  }

  public long createUser(String name, Integer age, Type type) {
    final User user = new User(name, age, type);
    final User savedUser = userRepo.save(user);

    return savedUser.getId();
  }

  @Transactional
  public User addUser(User user) {
    User savedUser = userRepo.save(user);
    return savedUser;
  }

  public void updateUser(long id, String name, Integer age, Type type) throws IllegalArgumentException {
    final Optional<User> maybeUser = userRepo.findById(id);

    if (maybeUser.isEmpty())
      throw new IllegalArgumentException("Invalid user ID");

    final User user = maybeUser.get();
    if (age != null) user.setAge(age);
    if (name != null && !name.isBlank()) user.setName(name);
    if (type != null) user.setType(type);
    userRepo.save(user);
  }

  public void deleteUser(long id) {
    userRepo.deleteById(id);
  }
}
