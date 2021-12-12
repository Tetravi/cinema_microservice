package com.cinema.servicehalls.service.impl;

import com.cinema.servicehalls.repo.model.Hall;
import com.cinema.servicehalls.service.HallService;
import com.cinema.servicehalls.repo.HallRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class HallServiceImpl implements HallService {

  private final HallRepo hallRepo;

  public List<Hall> fetchAllHalls() {
    return hallRepo.findAll();
  }

  public Hall fetchHallById(long id) throws IllegalArgumentException {
    final Optional<Hall> maybeHall = hallRepo.findById(id);

    if (maybeHall.isPresent())
      return maybeHall.get();
    else
      throw new IllegalArgumentException("Invalid hall ID");
  }

  public long createHall(Integer lines, Integer seats, String screenType) {
    final Hall hall = new Hall(lines, seats, screenType);
    final Hall savedHall = hallRepo.save(hall);

    return savedHall.getId();
  }

  @Transactional
  public Hall addHall(Hall hall) {
    Hall savedHall = hallRepo.save(hall);
    return savedHall;
  }

  public void updateHall(long id, Integer lines, Integer seats, String screenType) throws IllegalArgumentException {
    final Optional<Hall> maybeHall = hallRepo.findById(id);

    if (maybeHall.isEmpty())
      throw new IllegalArgumentException("Invalid hall ID");

    final Hall hall = maybeHall.get();
    if (lines != null) hall.setLines(lines);
    if (screenType != null && !screenType.isBlank()) hall.setScreenType(screenType);
    if (seats != null) hall.setSeats(seats);
    hallRepo.save(hall);
  }

  public void deleteHall(long id) {
    hallRepo.deleteById(id);
  }
}
