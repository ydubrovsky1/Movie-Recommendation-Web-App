package com.techelevator.dao;

import com.techelevator.model.Movie;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.sql.Array;

public class JdbcMovieDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcMovieDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Movie findMovieById(int movieId) {
        String sql = "SELECT movie_id, title, " +
                "overview, runtime, director, actors, release_date, rating, " +
                "certification, genres FROM movies " +
                "WHERE movie_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, movieId);

        if (results.next()) {

        }
        return null;
    }
        //SELECT * from movies where 20 = ANY(genres);
    public Movie mapRowToMovie(SqlRowSet rs) {
        Movie movie = new Movie();
        movie.setMovieId(rs.getInt("movie_id"));
        movie.setTitle(rs.getString("title"));
        movie.setOverview(rs.getString("overview"));
        movie.setRuntime(rs.getInt("runtime"));
        movie.setDirector(rs.getString("director"));
        //Array g = rs.getArray
        return movie;
    }
}
