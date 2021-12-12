package com.cinema.servicefilms.service;

import com.cinema.servicefilms.repo.model.Film;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface FilmService {
  List<Film> fetchAllFilms();
  Film fetchFilmById(Long id) throws IllegalArgumentException;
  Long createFilm(String title, Integer duration, String description, String startDate, String finishDate);
  public Film addFilm(Film film);
  void updateFilm(Long id, String title, Integer duration, String description, String startDate, String finishDate) throws IllegalArgumentException;
  void deleteFilm(Long id);
}
