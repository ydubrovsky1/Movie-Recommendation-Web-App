package com.techelevator.controller;

import com.techelevator.dao.GenreDao;
import com.techelevator.dao.JdbcGenreDao;
import com.techelevator.dao.JdbcMovieDao;
import com.techelevator.dao.MovieDao;
import com.techelevator.model.Genre;
import com.techelevator.model.Movie;
import com.techelevator.model.User;
import com.techelevator.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.quartz.QuartzProperties;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.security.Principal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//Access-Control-Allow-Origin: *

@RestController
@CrossOrigin

public class MovieController {
    @Autowired
    private GenreDao genreDao;
    @Autowired
    private MovieDao movieDao;
    private MovieService movieService;


    public MovieController(GenreDao genreDao, MovieDao movieDao, MovieService movieService) throws SQLException {
        this.genreDao = genreDao;
        this.movieDao = movieDao;
        this.movieService = movieService;

    }

    @RequestMapping(path = "/genre", method = RequestMethod.POST)
    public List<Genre> updateGenres(@RequestBody @Valid User user) {
        return genreDao.save(user.getId(), user.getPreferredGenres());
    }

    @RequestMapping(path = "/movie/{id}", method = RequestMethod.GET)
    public Movie getMovieById(@PathVariable int id) throws SQLException {
        return movieDao.findMovieById(id);
    }

    @RequestMapping(path = "/addFavorite", method = RequestMethod.POST) //addfavorite
    public Movie addMovie(@RequestBody @Valid User user) throws SQLException {
//        Movie movie = movieService.getMovie(user.getFavoriteMovieId());
//        if (movieDao.findMovieById(user.getFavoriteMovieId()).getMovieId() != user.getFavoriteMovieId()) {
//            movieDao.saveMovie(movie);
//        }
        return movieDao.findMovieById(user.getFavoriteMovieId());
    }

}
