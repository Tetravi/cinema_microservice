package com.cinema.servicehalls.repo;

import com.cinema.servicehalls.repo.model.Hall;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HallRepo extends JpaRepository<Hall, Long> {
}
