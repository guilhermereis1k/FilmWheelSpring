package com.example.FilmWheel.model.UserMovieRatingDTO;

import lombok.Data;

@Data
public class UserMovieRatingDTO {
    private Long userId;
    private Long movieId;
    private Double rating;
}
