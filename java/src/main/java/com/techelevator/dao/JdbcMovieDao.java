package com.techelevator.dao;

<<<<<<< HEAD
import com.techelevator.model.Genre;
import com.techelevator.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

@Component
public class JdbcMovieDao implements MovieDao{

    private JdbcTemplate jdbcTemplate;
    Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/final_capstone?user=final_capstone_appuser&password=finalcapstone");
    Statement stmt = con.createStatement();

    public JdbcMovieDao() throws SQLException {
    }


    public Movie findMovieById(int movieId) throws SQLException {

        Movie movie = new Movie();

=======
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
>>>>>>> 4cfa76c5696a621365b4243859b8746fd745902c
        String sql = "SELECT movie_id, title, " +
                "overview, runtime, director, actors, release_date, rating, " +
                "certification, genres FROM movies " +
                "WHERE movie_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, movieId);

<<<<<<< HEAD
        PreparedStatement pstmt = con.prepareStatement("SELECT movie_id, title, " +
                "overview, runtime, director, actors, release_date, rating, " +
                "certification, genres FROM movies " +
                "WHERE movie_id = ?;");

        pstmt.setInt(1, movieId);

        ResultSet rs2 = pstmt.executeQuery();
        if (rs2.next()) {
            Array g = rs2.getArray("genres");
            int[] actualGenres = (int[]) g.getArray();
            Array a = rs2.getArray("actors");
            String[] actualActors = (String[]) a.getArray();
            if (results.next()) {
                movie = mapRowToMovie(results, actualGenres, actualActors);
            }
        }

        return movie;
    }
    //SELECT * from movies where 20 = ANY(genres);
    public Movie mapRowToMovie(SqlRowSet rs, int[] genres, String[] actors) {
=======
        if (results.next()) {

        }
        return null;
    }
        //SELECT * from movies where 20 = ANY(genres);
    public Movie mapRowToMovie(SqlRowSet rs) {
>>>>>>> 4cfa76c5696a621365b4243859b8746fd745902c
        Movie movie = new Movie();
        movie.setMovieId(rs.getInt("movie_id"));
        movie.setTitle(rs.getString("title"));
        movie.setOverview(rs.getString("overview"));
        movie.setRuntime(rs.getInt("runtime"));
<<<<<<< HEAD
        if (rs.getString("director") != null) {
            movie.setDirector(rs.getString("director"));
        }
        List<Genre> actualGenres = new ArrayList<>();
        if (genres != null) {
            for (int i = 0; i < genres.length; i++) {
                actualGenres.add(new Genre(genres[i]));
            }
            movie.setGenre(actualGenres);
        }
        if (actors != null) {
            movie.setActors(List.of(actors));
        }
        movie.setReleaseDate(rs.getString("release_date"));
        movie.setRating(rs.getDouble("rating"));
        movie.setCertification(rs.getString("certification"));

        return movie;
    }
}
=======
        movie.setDirector(rs.getString("director"));
        //Array g = rs.getArray
        return movie;
    }
}
>>>>>>> 4cfa76c5696a621365b4243859b8746fd745902c
