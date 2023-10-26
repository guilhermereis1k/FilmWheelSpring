package com.example.FilmWheel.services;

import com.example.FilmWheel.model.Movie;
import com.example.FilmWheel.model.Rating;
import com.example.FilmWheel.model.User;
import com.example.FilmWheel.repositories.UserRepository;
import jakarta.persistence.TypedQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    Logger logger = LoggerFactory.getLogger(MovieService.class);

    public User insert(User user){
        return userRepository.save(user);
    }

    public void delete(User user){
        userRepository.delete(user);
    }

    public User put(User user){
        try {
            userRepository.findById(user.getId());
            userRepository.save(user);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        return userRepository.save(user);
    }

    public User findById(Long id){
        return userRepository.findById(id).orElseThrow(() -> {
            logger.error("User not found with id {}", id);
            return new RuntimeException("User not find with id " + id);
        });
    }

    public List<Rating> getRatingsByUserId(Long id) {
        User selectedUser = userRepository.findById(id).orElseThrow(() -> {
            logger.error("Movie not found with id {}", id);
            return new RuntimeException("Movie not find with id " + id);
        });

        return selectedUser.getRatings();
    }

    public List<Movie> getMoviesVotedByUser(Long userId) {
        List<Movie> moviesVotedByUser = userRepository.findMoviesVotedByUser(userId);
        return moviesVotedByUser;
    }
}
