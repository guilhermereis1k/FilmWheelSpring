package com.example.FilmWheel.repositories;

import com.example.FilmWheel.model.Movie;
import com.example.FilmWheel.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT r.movie FROM Rating r WHERE r.user.id = :userId")
    List<Movie> findMoviesVotedByUser(Long userId);
}
