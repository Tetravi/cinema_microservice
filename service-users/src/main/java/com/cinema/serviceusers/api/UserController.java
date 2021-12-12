package com.cinema.serviceusers.api;

import com.cinema.serviceusers.repo.model.Type;
import com.cinema.serviceusers.repo.model.User;
import com.cinema.serviceusers.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public final class UserController {

  private final UserServiceImpl userServiceImpl;

  @GetMapping
  public ResponseEntity<List<User>> index() {
    final List<User> users = userServiceImpl.fetchAllUsers();

    return ResponseEntity.ok(users);
  }

  @GetMapping("/{id}")
  public ResponseEntity<User> showById(@PathVariable long id) {
    try {
      final User user = userServiceImpl.fetchUserById(id);

      return ResponseEntity.ok(user);
    } catch (IllegalArgumentException e) {
      return ResponseEntity.notFound().build();
    }
  }

  /*@PostMapping
  public User createUser(@RequestBody User user) {
    return userServiceImpl.addUser(user);
  }*/

  @PostMapping
  public ResponseEntity<Void> create(@RequestBody com.cinema.serviceusers.api.dto.User user) {
    final String name = user.getName();
    final Integer age = user.getAge();
    final Type type = user.getType();
    final Long userId = userServiceImpl.createUser(name, age, type);
    final String userUri = String.format("/users/%d", userId);

    return ResponseEntity.created(URI.create(userUri)).build();
  }

  @PatchMapping("/{id}")
  public ResponseEntity<Void> change(@PathVariable long id, @RequestBody com.cinema.serviceusers.api.dto.User user) {
    final String name = user.getName();
    final Integer age = user.getAge();
    final Type type = user.getType();

    try {
      userServiceImpl.updateUser(id, name, age, type);

      return ResponseEntity.noContent().build();
    } catch (IllegalArgumentException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable long id) {
    userServiceImpl.deleteUser(id);

    return ResponseEntity.noContent().build();
  }
}
