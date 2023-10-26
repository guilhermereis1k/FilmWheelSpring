package com.example.FilmWheel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String director;

    @JsonIgnore
    @OneToMany(mappedBy = "movie")
    private List<Rating> ratings;

    private Double averageRating;

    public void updateAverageRating() {
        List<Rating> ratingsList = this.getRatings();
        if (ratingsList.isEmpty()) {
            this.averageRating = 0.0;
        } else {
            double totalSum = 0.0;

            for (Rating rating : ratingsList) {
                totalSum += rating.getRating();
            }

            this.averageRating = totalSum / ratingsList.size();
        }
    }
}
