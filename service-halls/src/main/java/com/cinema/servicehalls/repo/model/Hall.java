package com.cinema.servicehalls.repo.model;

import javax.persistence.*;


@Entity
@Table(name = "halls")
public final class Hall {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  private Integer lines;
  private Integer seats;
  private String screenType;

  public Hall() {
  }

  public Hall(Integer lines, Integer seats, String screenType) {
    this.lines = lines;
    this.seats = seats;
    this.screenType = screenType;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public Integer getLines() {
    return lines;
  }

  public void setLines(Integer lines) {
    this.lines = lines;
  }

  public Integer getSeats() {
    return seats;
  }

  public void setSeats(Integer seats) {
    this.seats = seats;
  }

  public String getScreenType() {
    return screenType;
  }

  public void setScreenType(String screenType) {
    this.screenType = screenType;
  }
}
