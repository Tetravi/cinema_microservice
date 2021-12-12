package com.cinema.serviceseances.repo.model;

import javax.persistence.*;
import java.util.UUID;


@Entity
@Table(name = "seances")
public final class Seance {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private Long film;
  private Long hall;
  private String time;

  public Seance() {
  }

  public Seance(Long film, Long hall, String time) {
    //this.id = UUID.randomUUID();
    this.film = film;
    this.hall = hall;
    this.time = time;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public Long getFilm() {
    return film;
  }

  public void setFilm(Long film) {
    this.film = film;
  }

  public Long getHall() {
    return hall;
  }

  public void setHall(Long hall) {
    this.hall = hall;
  }

  public Long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }
}
