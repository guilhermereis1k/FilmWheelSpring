package com.example.FilmWheel.repositories;

import com.example.FilmWheel.model.Movie;
import com.example.FilmWheel.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long> {
}
