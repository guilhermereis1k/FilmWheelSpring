package com.example.FilmWheel.controllers;

import com.example.FilmWheel.model.Movie;
import com.example.FilmWheel.model.Rating;
import com.example.FilmWheel.model.User;
import com.example.FilmWheel.model.UserMovieRatingDTO.UserMovieRatingDTO;
import com.example.FilmWheel.services.MovieService;
import com.example.FilmWheel.services.RatingService;
import com.example.FilmWheel.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "rating")
public class RatingController {

    @Autowired
    RatingService ratingService;

    @Autowired
    UserService userService;

    @Autowired
    MovieService movieService;

    Logger logger = LoggerFactory.getLogger(MovieService.class);


    @PostMapping
    public ResponseEntity<Rating> insert(@RequestBody UserMovieRatingDTO userMovieRatingDTO) {
        Double ratingValue = userMovieRatingDTO.getRating();
        if(ratingValue < 0 || ratingValue > 10) {
            logger.error("Rating out of range, entered rating: " + ratingValue);
            throw  new RuntimeException("Rating out of range (should be 0 to 10), entered rating: " + ratingValue);
        } else {
            User user = userService.findById(userMovieRatingDTO.getUserId());
            Movie movie = movieService.findById(userMovieRatingDTO.getMovieId());

            Rating rating = ratingService.insert(movie, user, ratingValue);
            return ResponseEntity.ok().body(rating);
        }
    }
}
