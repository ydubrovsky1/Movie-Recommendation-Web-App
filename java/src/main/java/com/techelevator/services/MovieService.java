package com.techelevator.services;

import com.techelevator.model.Genre;
import com.techelevator.model.Movie;

import java.util.List;

public interface MovieService {

    Movie getMovie(int id);

    List<Movie> getMoviesByGenres(List<Genre> genres);

    List<Movie> getRecommendedMovies(Movie baseMovie);
}
