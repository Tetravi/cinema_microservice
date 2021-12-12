package com.cinema.servicefilms.repo.model;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "films")
public final class Film {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String title;
  private Integer duration;
  private String description;
  private String startDate;
  private String finishDate;

  public Film() {
  }

  public Film(String title, Integer duration, String description, String startDate, String finishDate) {
    //this.id = UUID.randomUUID();
    this.title = title;
    this.duration = duration;
    this.description = description;
    this.startDate = startDate;
    this.finishDate = finishDate;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Integer getDuration() {
    return duration;
  }

  public void setDuration(Integer duration) {
    this.duration = duration;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


  public String getStartDate() {
    return startDate;
  }

  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }

  public String getFinishDate() {
    return finishDate;
  }

  public void setFinishDate(String finishDate) {
    this.finishDate = finishDate;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
