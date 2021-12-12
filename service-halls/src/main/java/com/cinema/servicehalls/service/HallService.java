package com.cinema.servicehalls.service;

import com.cinema.servicehalls.repo.model.Hall;

import java.util.Date;
import java.util.List;

public interface HallService {
  List<Hall> fetchAllHalls();
  Hall fetchHallById(long id) throws IllegalArgumentException;
  long createHall(Integer lines, Integer seats, String screenType);
  public Hall addHall(Hall hall);
  void updateHall(long id, Integer lines, Integer seats, String screenType) throws IllegalArgumentException;
  void deleteHall(long id);
}
