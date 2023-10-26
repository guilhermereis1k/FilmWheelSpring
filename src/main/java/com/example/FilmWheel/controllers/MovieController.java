package com.example.FilmWheel.controllers;

import com.example.FilmWheel.model.Movie;
import com.example.FilmWheel.model.Rating;
import com.example.FilmWheel.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.util.List;

@RestController
@RequestMapping(value = "movie")
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping
    public ResponseEntity<Movie> insert(@RequestBody Movie movie){
        movieService.insert(movie);
        return ResponseEntity.ok().body(movie);
    }

    @GetMapping
    public ResponseEntity<Movie> findById(@RequestBody Long id){
        Movie movie = movieService.findById(id);
        return ResponseEntity.ok().body(movie);
    }

    @DeleteMapping
    public ResponseEntity<Movie> delete(@RequestBody Movie movie){
        movieService.delete(movie);
        return ResponseEntity.ok().body(movie);
    }

    @PutMapping
    public ResponseEntity<Movie> put(@RequestBody Movie movie){
        movieService.put(movie);
        return ResponseEntity.ok().body(movie);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Movie>> findAll(){
        List<Movie> movieList = movieService.findAll();
        return ResponseEntity.ok().body(movieList);
    }

    @GetMapping("/allratings")
    public ResponseEntity<List<Rating>> getRatingsByMovieId(@RequestBody Long id) {
         List<Rating> ratingsList = movieService.getRatingsByMovieId(id);
         return ResponseEntity.ok().body(ratingsList);
    }

    @GetMapping("/rating")
    public ResponseEntity<String> getRatingAverage(@RequestBody Long id) {
        Double averageRating = movieService.getAverageRating(id);

        DecimalFormat df = new DecimalFormat("#.##");
        String formattedAverageNumber = df.format(averageRating);

        return ResponseEntity.ok().body(formattedAverageNumber);
    }

}
