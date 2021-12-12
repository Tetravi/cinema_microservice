package com.cinema.serviceusers.repo.model;

import javax.persistence.*;


@Entity
@Table(name = "users")
public final class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  private String name;
  private Integer age;

  @Enumerated(EnumType.STRING)
  private Type type;

  public User() {
  }

  public User(String name, Integer age, Type type) {
    this.name = name;
    this.age = age;
    this.type = type;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public Type getType() {
    return type;
  }

  public void setType(Type type) {
    this.type = type;
  }
}
