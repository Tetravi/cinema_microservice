package com.cinema.serviceseances.api;


import com.cinema.serviceseances.repo.model.Seance;
import com.cinema.serviceseances.service.impl.SeanceServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/seances")
@RestController
public final class SeanceController {

  private final SeanceServiceImpl seanceServiceImpl;

  @GetMapping
  public ResponseEntity<List<Seance>> index() {
    final List<Seance> seances = seanceServiceImpl.fetchAllSeances();

    return ResponseEntity.ok(seances);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Seance> showById(@PathVariable Long id) {
    try {
      final Seance seance = seanceServiceImpl.fetchSeanceById(id);

      return ResponseEntity.ok(seance);
    } catch (IllegalArgumentException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping
  public ResponseEntity<Void> create(@RequestBody com.cinema.serviceseances.api.dto.Seance seance) {
    final Long film = seance.getFilm();
    final Long hall = seance.getHall();
    final String time = seance.getTime();
    final Long seanceId = seanceServiceImpl.createSeance(film, hall, time);
    final String seanceUri = String.format("/seances/%d", seanceId);

    return ResponseEntity.created(URI.create(seanceUri)).build();
  }

  /*@PostMapping
  public Seance createSeance(@RequestBody Seance seance) {
    return seanceServiceImpl.addSeance(seance);
  }*/

  @PatchMapping("/{id}")
  public ResponseEntity<Void> change(@PathVariable Long id, @RequestBody com.cinema.serviceseances.api.dto.Seance seance) {
    final Long film = seance.getFilm();
    final Long hall = seance.getHall();
    final String time = seance.getTime();

    try {
      seanceServiceImpl.updateSeance(id, film, hall, time);

      return ResponseEntity.noContent().build();
    } catch (IllegalArgumentException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable Long id) {
    seanceServiceImpl.deleteSeance(id);

    return ResponseEntity.noContent().build();
  }
}
