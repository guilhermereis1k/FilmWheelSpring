package com.example.FilmWheel.controllers;

import com.example.FilmWheel.model.Movie;
import com.example.FilmWheel.model.Rating;
import com.example.FilmWheel.model.User;
import com.example.FilmWheel.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<User> insert(@RequestBody User user){
        userService.insert(user);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping
    public ResponseEntity<User> findById(@RequestBody Long id){
        User user = userService.findById(id);
        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping
    public ResponseEntity<User> delete(@RequestBody User user){
        userService.delete(user);
        return ResponseEntity.ok().body(user);
    }

    @PutMapping
    public ResponseEntity<User> put(@RequestBody User user){
        userService.put(user);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/ratings")
    public ResponseEntity<List<Rating>> getRatingsByUserId(@RequestBody Long movieId){
        User user = userService.findById(movieId);
        return ResponseEntity.ok().body(user.getRatings());
    }

    @GetMapping("/movies")
    public ResponseEntity<List<Movie>> getMoviesVotedByUser(@RequestBody Long userId){
        List<Movie> moviesList = userService.getMoviesVotedByUser(userId);
        return ResponseEntity.ok().body(moviesList);
    }
}
