package com.cinema.serviceseances.service;

import com.cinema.serviceseances.repo.model.Seance;

import java.util.List;
import java.util.UUID;

public interface SeanceService {
  List<Seance> fetchAllSeances();
  Seance fetchSeanceById(Long id) throws IllegalArgumentException;
  Long createSeance(Long film, Long hall, String time);
  Seance addSeance(Seance seance);
  void updateSeance(Long id, Long film, Long hall, String time) throws IllegalArgumentException;
  void deleteSeance(Long id);
}
