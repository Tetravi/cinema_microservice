package com.cinema.servicefilms.api;

import com.cinema.servicefilms.repo.model.Film;
import com.cinema.servicefilms.service.impl.FilmServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/films")
@RestController
public final class FilmController {

  private final FilmServiceImpl filmServiceImpl;

  @GetMapping
  public ResponseEntity<List<com.cinema.servicefilms.repo.model.Film>> index() {
    final List<com.cinema.servicefilms.repo.model.Film> films = filmServiceImpl.fetchAllFilms();

    return ResponseEntity.ok(films);
  }

  @GetMapping("/{id}")
  public ResponseEntity<com.cinema.servicefilms.repo.model.Film> showById(@PathVariable Long id) {
    try {
      final com.cinema.servicefilms.repo.model.Film film = filmServiceImpl.fetchFilmById(id);

      return ResponseEntity.ok(film);
    } catch (IllegalArgumentException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("/dto/{id}")
  public ResponseEntity<com.cinema.servicefilms.api.dto.Film> getDtoById(@PathVariable Long id) {
    try {
      Film film = filmServiceImpl.fetchFilmById(id);
      com.cinema.servicefilms.api.dto.Film filmDto = new com.cinema.servicefilms.api.dto.Film(film.getTitle(),
              film.getDuration(), film.getDescription(), film.getStartDate(), film.getFinishDate());

      return ResponseEntity.ok(filmDto);
    } catch (IllegalArgumentException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping
  public ResponseEntity<Void> create(@RequestBody com.cinema.servicefilms.api.dto.Film film) {
    final String title = film.getTitle();
    final Integer duration = film.getDuration();
    final String description = film.getDescription();
    final String startDate = film.getStartDate();
    final String finishDate = film.getFinishDate();
    final Long filmId = filmServiceImpl.createFilm(title, duration, description, startDate, finishDate);
    final String filmUri = String.format("/films/%d", filmId);

    return ResponseEntity.created(URI.create(filmUri)).build();
  }


  /*@PostMapping
  public Film createFilm(@RequestBody Film film) {
    return filmServiceImpl.addFilm(film);
  }*/

  @PatchMapping("/{id}")
  public ResponseEntity<Void> change(@PathVariable Long id, @RequestBody com.cinema.servicefilms.api.dto.Film film) {
    final String title = film.getTitle();
    final Integer duration = film.getDuration();
    final String description = film.getDescription();
    final String startDate = film.getStartDate();
    final String finishDate = film.getFinishDate();

    try {
      filmServiceImpl.updateFilm(id, title, duration, description, startDate, finishDate);

      return ResponseEntity.noContent().build();
    } catch (IllegalArgumentException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable Long id) {
    filmServiceImpl.deleteFilm(id);

    return ResponseEntity.noContent().build();
  }
}
