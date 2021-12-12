package com.cinema.serviceseances.service.impl;


import com.cinema.serviceseances.api.dto.Film;
import com.cinema.serviceseances.repo.model.Seance;
import com.cinema.serviceseances.service.SeanceService;
import com.cinema.serviceseances.repo.SeanceRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class SeanceServiceImpl implements SeanceService {

  private final String filmUrlAdress ="http://service-films:8080/films";

  private final SeanceRepo seanceRepo;

  public List<Seance> fetchAllSeances() {
    return seanceRepo.findAll();
  }

  public Seance fetchSeanceById(Long id) throws IllegalArgumentException {
    final Optional<Seance> maybeSeance = seanceRepo.findById(id);

    if (maybeSeance.isPresent())
      return maybeSeance.get();
    else
      throw new IllegalArgumentException("Invalid seance ID");
  }

  private boolean checkForCorrectness(Long filmId) {
    final RestTemplate restTemplate = new RestTemplate();
    final HttpEntity<Long> userRequest = new HttpEntity<>(filmId);

    final ResponseEntity<Film> userResponse = restTemplate
            .exchange(filmUrlAdress + "/dto/" + filmId,
                    HttpMethod.GET, userRequest, Film.class);

    return userResponse.getStatusCode() != HttpStatus.NOT_FOUND;
  }

  public Long createSeance(Long film, Long hall, String time) {
    final Seance seance = new Seance(film, hall, time);

    if (!checkForCorrectness(film)) {
      throw new IllegalArgumentException("Incorrect request");
    }
    else {
      final Seance savedSeance = seanceRepo.save(seance);
      return savedSeance.getId();
    }
  }

  @Transactional
  public Seance addSeance(Seance seance) {
    Seance savedSeance = seanceRepo.save(seance);
    return savedSeance;
  }

  public void updateSeance(Long id, Long film, Long hall, String time) throws IllegalArgumentException {
    final Optional<Seance> maybeSeance = seanceRepo.findById(id);

    if (maybeSeance.isEmpty())
      throw new IllegalArgumentException("Invalid seance ID");

    final Seance seance = maybeSeance.get();
    if (film != null && film != -1) {
      if (!checkForCorrectness(film)) {
        throw new IllegalArgumentException("Invalid film id");
      }

      seance.setFilm(film);
    }
    if (time != null && !time.isBlank()) seance.setTime(time);
    if (hall != null) seance.setHall(hall);
    seanceRepo.save(seance);
  }

  public void deleteSeance(Long id) {
    seanceRepo.deleteById(id);
  }
}
