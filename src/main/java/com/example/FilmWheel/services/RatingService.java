package com.example.FilmWheel.services;

import com.example.FilmWheel.model.Movie;
import com.example.FilmWheel.model.Rating;
import com.example.FilmWheel.model.User;
import com.example.FilmWheel.repositories.MovieRepository;
import com.example.FilmWheel.repositories.RatingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Service
public class RatingService {

    @Autowired
    RatingRepository ratingRepository;

    @Autowired
    MovieRepository movieRepository;

    Logger logger = LoggerFactory.getLogger(MovieService.class);

    public Rating insert(Movie movie, User user, Double ratingValue){
            Rating rating = new Rating(ratingValue, movie, user);
            movie.updateAverageRating();
            movieRepository.save(movie);
            return ratingRepository.save(rating);
    }


}
