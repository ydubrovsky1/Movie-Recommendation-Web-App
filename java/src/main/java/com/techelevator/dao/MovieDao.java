package com.techelevator.dao;

import com.techelevator.model.Movie;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.sql.SQLException;

public interface MovieDao {

    Movie findMovieById(int movieId) throws SQLException;
    Movie mapRowToMovie(SqlRowSet rs, Integer[] genres, String[] actors);

}
