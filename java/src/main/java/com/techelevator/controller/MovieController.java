package com.techelevator.controller;

import com.techelevator.dao.GenreDao;
import com.techelevator.dao.JdbcGenreDao;
import com.techelevator.dao.JdbcMovieDao;
import com.techelevator.dao.MovieDao;
import com.techelevator.model.*;
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
    public List<Genre> updateGenres(@RequestBody @Valid UserGenre userGenre) {
        return genreDao.save(userGenre.getUserId(), userGenre.getGenreIdsFromStrings());
    }


   @RequestMapping(path = "/genre/{userId}/{genreId}", method = RequestMethod.DELETE)
   public boolean deleteGenre(@PathVariable int userId, @PathVariable int genreId ) {
        return genreDao.deleteGenreFromUser(userId,genreId);
    }

    @RequestMapping(path = "/movie/{id}", method = RequestMethod.GET)
    public Movie getMovieById(@PathVariable int id) throws SQLException {
        return movieDao.findMovieById(id);
    }

   @RequestMapping(path = "/addFavorite", method = RequestMethod.POST) //addfavorite
    public boolean addMovie(@RequestBody @Valid UserMovie userMovie) throws SQLException {
        //if already in faves return false
        if(movieDao.checkIfInFavorites(userMovie.getUserId(), userMovie.getMovieId())){
            return false;
       }
        //if movie already in movies, just save userId+movieId to faves
       if(movieDao.findMovieById(userMovie.getMovieId()) != null){
           return movieDao.addMovieToFavorites(userMovie.getMovieId(), userMovie.getUserId());
       }

        Movie movie = movieService.getMovie(userMovie.getMovieId());
        if(movie != null){
           movieDao.saveMovie(movie);
      }
       return movieDao.addMovieToFavorites(userMovie.getMovieId(), userMovie.getUserId());

   }

    @RequestMapping(path = "/getGenres", method = RequestMethod.POST)
    public List<Genre> getGenres(@RequestBody UserGenre userGenre) {
        return genreDao.getGenresByUser(userGenre.getUserId());
    }

}
