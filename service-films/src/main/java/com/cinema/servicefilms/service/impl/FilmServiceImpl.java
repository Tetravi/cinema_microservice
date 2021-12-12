package com.cinema.servicefilms.service.impl;

import com.cinema.servicefilms.service.FilmService;
import com.cinema.servicefilms.repo.FilmRepo;
import com.cinema.servicefilms.repo.model.Film;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FilmServiceImpl implements FilmService {

  private final FilmRepo filmRepo;

  public List<Film> fetchAllFilms() {
    return filmRepo.findAll();
  }

  public Film fetchFilmById(Long id) throws IllegalArgumentException {
    final Optional<Film> maybeFilm = filmRepo.findById(id);

    if (maybeFilm.isPresent())
      return maybeFilm.get();
    else
      throw new IllegalArgumentException("Invalid film ID");
  }

  public Long createFilm(String title, Integer duration, String description, String startDate, String finishDate) {
    final Film film = new Film(title, duration, description, startDate, finishDate);
    final Film savedFilm = filmRepo.save(film);

    return savedFilm.getId();
  }

  @Transactional
  public Film addFilm(Film film) {
    Film savedFilm = filmRepo.save(film);
    return savedFilm;
  }

  public void updateFilm(Long id, String title, Integer duration, String description, String startDate, String finishDate) throws IllegalArgumentException {
    final Optional<Film> maybeFilm = filmRepo.findById(id);

    if (maybeFilm.isEmpty())
      throw new IllegalArgumentException("Invalid film ID");

    final Film film = maybeFilm.get();
    if (title != null && !title.isBlank()) film.setTitle(title);
    if (duration != null) film.setDuration(duration);
    if (description != null && !description.isBlank()) film.setDescription(description);
    if (startDate != null) film.setStartDate(startDate);
    if (finishDate != null) film.setFinishDate(finishDate);
    filmRepo.save(film);
  }

  public void deleteFilm(Long id) {
    filmRepo.deleteById(id);
  }
}
