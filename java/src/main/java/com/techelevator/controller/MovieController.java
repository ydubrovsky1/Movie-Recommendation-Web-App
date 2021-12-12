package com.techelevator.controller;

import com.techelevator.dao.GenreDao;
import com.techelevator.dao.JdbcGenreDao;
import com.techelevator.dao.JdbcMovieDao;
import com.techelevator.model.Genre;
import com.techelevator.model.User;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

//Access-Control-Allow-Origin: *

@RestController
@CrossOrigin
public class MovieController {
    private GenreDao genreDao;
    //private MovieDao movieDao;

    public MovieController(){
        this.genreDao = new JdbcGenreDao();

    }

    @RequestMapping(path = "/genre", method = RequestMethod.POST)
    public List<Genre> updateGenres(@RequestBody @Valid User user) {
        return genreDao.save(user.getId(), user.getPreferredGenres());
    }

}
