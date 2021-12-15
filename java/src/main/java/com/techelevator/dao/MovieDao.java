package com.techelevator.dao;

import com.techelevator.model.Movie;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.sql.SQLException;
import java.util.List;

public interface MovieDao {

    Movie findMovieById(int movieId) throws SQLException;
    //Movie mapRowToMovie(SqlRowSet rs, Integer[] genres, String[] actors);
    Movie mapRowToMovie(SqlRowSet rs);

    boolean saveMovie(Movie movie) throws SQLException;

    boolean checkIfInFavorites(int userId, int movieId);

    boolean addMovieToFavorites(int movieId, int userId);
    List<Movie> getFavoriteMoviesByUser(int userId);

    public boolean addMovieToAbhorred(int movieId, int userId);

    public boolean checkIfInAbhorred(int userId, int movieId);
}
