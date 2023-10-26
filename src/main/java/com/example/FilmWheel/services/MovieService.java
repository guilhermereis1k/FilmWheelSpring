package com.example.FilmWheel.services;

import com.example.FilmWheel.model.Movie;
import com.example.FilmWheel.model.Rating;
import com.example.FilmWheel.repositories.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    Logger logger = LoggerFactory.getLogger(MovieService.class);

    public Movie insert(Movie movie){
        return movieRepository.save(movie);
    }

    public void delete(Movie movie){
        movieRepository.delete(movie);
    }

    public Movie put(Movie movie){
        try {
            movieRepository.findById(movie.getId());
            movieRepository.save(movie);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        return movieRepository.save(movie);
    }

    public List<Movie> findAll() {
    return movieRepository.findAll();
    }

    public Movie findById(Long id){
        Movie selectedMovie = movieRepository.findById(id).orElseThrow(() -> {
            logger.error("Movie not found with id {}", id);
            return new RuntimeException("Movie not find with id " + id);
        });

        return selectedMovie;
    }

    public List<Rating> getRatingsByMovieId(Long id) {
        Movie selectedMovie = movieRepository.findById(id).orElseThrow(() -> {
            logger.error("Movie not found with id {}", id);
            return new RuntimeException("Movie not find with id " + id);
        });

        selectedMovie.updateAverageRating();
        movieRepository.save(selectedMovie);

        return selectedMovie.getRatings();
    }

    public Double getAverageRating(Long id) {
        Movie selectedMovie = movieRepository.findById(id).orElseThrow(() -> {
            logger.error("Movie not found with id {}", id);
            return new RuntimeException("Movie not find with id " + id);
        });

        selectedMovie.updateAverageRating();
        movieRepository.save(selectedMovie);

        return selectedMovie.getAverageRating();
    }
}
