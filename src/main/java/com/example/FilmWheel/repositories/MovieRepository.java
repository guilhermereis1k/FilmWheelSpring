package com.example.FilmWheel.repositories;

import com.example.FilmWheel.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
