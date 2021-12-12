package com.cinema.servicehalls.api;

import com.cinema.servicehalls.repo.model.Hall;
import com.cinema.servicehalls.service.impl.HallServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/halls")
@RestController
public final class HallController {

  private final HallServiceImpl hallServiceImpl;

  @GetMapping
  public ResponseEntity<List<Hall>> index() {
    final List<Hall> halls = hallServiceImpl.fetchAllHalls();

    return ResponseEntity.ok(halls);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Hall> showById(@PathVariable long id) {
    try {
      final Hall hall = hallServiceImpl.fetchHallById(id);

      return ResponseEntity.ok(hall);
    } catch (IllegalArgumentException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping
  public ResponseEntity<Void> create(@RequestBody com.cinema.servicehalls.api.dto.Hall hall) {
    final Integer lines = hall.getLines();
    final Integer seats = hall.getSeats();
    final String screenType = hall.getScreenType();
    final Long hallId = hallServiceImpl.createHall(lines, seats, screenType);
    final String hallUri = String.format("/halls/%d", hallId);

    return ResponseEntity.created(URI.create(hallUri)).build();
  }

  /*@PostMapping
  public Hall createHall(@RequestBody Hall hall) {
    return hallServiceImpl.addHall(hall);
  }*/


  @PatchMapping("/{id}")
  public ResponseEntity<Void> change(@PathVariable long id, @RequestBody com.cinema.servicehalls.api.dto.Hall hall) {
    final Integer lines = hall.getLines();
    final Integer seats = hall.getSeats();
    final String screenType = hall.getScreenType();

    try {
      hallServiceImpl.updateHall(id, lines, seats, screenType);

      return ResponseEntity.noContent().build();
    } catch (IllegalArgumentException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable long id) {
    hallServiceImpl.deleteHall(id);

    return ResponseEntity.noContent().build();
  }
}
