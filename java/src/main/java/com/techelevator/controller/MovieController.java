package com.techelevator.controller;

import com.techelevator.dao.GenreDao;
import com.techelevator.dao.JdbcGenreDao;
import com.techelevator.dao.JdbcMovieDao;
import com.techelevator.model.Genre;
import com.techelevator.model.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
public class MovieController {
    private GenreDao genreDao;
    //private MovieDao movieDao;

    public MovieController(){
        this.genreDao = new JdbcGenreDao();

    }

    @RequestMapping(path = "/genre", method = RequestMethod.POST)
    public List<Genre> updateGenres(@RequestBody User user) {
        return genreDao.save(user.getId(), user.getPreferredGenres());
    }

    @RequestMapping(path = "/")

}
