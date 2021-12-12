package com.cinema.servicefilms.repo;

import com.cinema.servicefilms.repo.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FilmRepo extends JpaRepository<Film, Long> {
}
