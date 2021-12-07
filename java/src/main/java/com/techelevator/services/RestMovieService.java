package com.techelevator.services;

import com.techelevator.model.Genre;
import com.techelevator.model.Movie;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class RestMovieService implements MovieService{

    //movies
    private static final String API_BASE_URL = "https://api.themoviedb.org/3/movie/";
    private static final String API_DISCOVER_URL = "https://api.themoviedb.org/3/discover/movie?api_key=fbc258611c329af8b2fa24446de942f6&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1&with_watch_monetization_types=flatrate&with_genres=";
    private final RestTemplate restTemplate = new RestTemplate();
    private static final String API_KEY = "?api_key=fbc258611c329af8b2fa24446de942f6&language=en-US";
    private static final String RECOMMENDED_MOVIE_URL = "/recommendations?api_key=fbc258611c329af8b2fa24446de942f6&language=en-US&page=1";


    @Override
    public Movie getMovie(int id) {
        Movie returnedMovie = new Movie();
        returnedMovie = restTemplate.getForObject(API_BASE_URL + id + API_KEY, Movie.class);
        return returnedMovie;
    }

    @Override
    public List<Movie> getMoviesByGenres(List<Genre> genres) {
        List<Movie> returnedMovies = new ArrayList<>();
        String myGenreUrl = "";
        for(Genre genre : genres){
            myGenreUrl = myGenreUrl + genre + "%2C";
        }
        returnedMovies = restTemplate.getForObject(API_DISCOVER_URL + myGenreUrl, List.class);
        return returnedMovies;
    }

    @Override
    public List<Movie> getRecommendedMovies(Movie baseMovie) {
        List<Movie> recommendedMovies = new ArrayList<>();
        recommendedMovies = restTemplate.getForObject(API_BASE_URL + baseMovie.getMovieId() + RECOMMENDED_MOVIE_URL, List.class);
        return recommendedMovies;
    }
}
