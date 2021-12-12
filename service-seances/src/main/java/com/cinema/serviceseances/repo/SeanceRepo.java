package com.cinema.serviceseances.repo;

import com.cinema.serviceseances.repo.model.Seance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SeanceRepo extends JpaRepository<Seance, Long> {
}
