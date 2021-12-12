package com.cinema.serviceusers.repo;

import com.cinema.serviceusers.repo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
}
