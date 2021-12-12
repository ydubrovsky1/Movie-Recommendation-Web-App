package com.techelevator.controller;

import com.techelevator.dao.GenreDao;
import com.techelevator.dao.JdbcGenreDao;
import com.techelevator.dao.JdbcMovieDao;
import com.techelevator.dao.MovieDao;
import com.techelevator.model.Genre;
import com.techelevator.model.Movie;
import com.techelevator.model.User;
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


    public MovieController(GenreDao genreDao, MovieDao movieDao) throws SQLException {
        this.genreDao = genreDao;
        this.movieDao = movieDao;

    }

    @RequestMapping(path = "/genre", method = RequestMethod.POST)
    public List<Genre> updateGenres(@RequestBody @Valid User user) {
        return genreDao.save(user.getId(), user.getPreferredGenres());
    }

    @RequestMapping(path = "/movie/{id}", method = RequestMethod.GET)
    public Movie getMovieById(@PathVariable int id) throws SQLException {
        return movieDao.findMovieById(id);
    }

}
