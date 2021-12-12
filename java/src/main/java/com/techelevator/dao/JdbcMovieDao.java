//package com.techelevator.dao;
//
//import com.techelevator.model.Genre;
//import com.techelevator.model.Movie;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.support.rowset.SqlRowSet;
//import org.springframework.stereotype.Component;
//
//import javax.sql.DataSource;
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
////import org.apache.commons.dbcp2.BasicDataSource;
//import org.springframework.stereotype.Service;
//
//@Service
//public class JdbcMovieDao implements MovieDao{
//
//    private JdbcTemplate jdbcTemplate;
//    Connection con;
//
//    {
//        try {
//            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/final_capstone?user=final_capstone_appuser&password=finalcapstone");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    Statement stmt;
//
//    {
//        try {
//            stmt = con.createStatement();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

//    public JdbcMovieDao(DataSource dataSource) {
//        this.jdbcTemplate = new JdbcTemplate(dataSource);
//    }

package com.techelevator.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.techelevator.model.Genre;
import com.techelevator.model.Movie;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.techelevator.model.User;

@Service
public class JdbcMovieDao implements MovieDao {

    private JdbcTemplate jdbcTemplate;


        Connection con;

    {
        try {
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/final_capstone?user=final_capstone_appuser&password=finalcapstone");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    Statement stmt;

    {
        try {
            stmt = con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public JdbcMovieDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

//    public JdbcMovieDao(JdbcTemplate jdbcTemplate) {
//    this.jdbcTemplate = jdbcTemplate;
//    }


    public Movie findMovieById(int movieId) throws SQLException {

        Movie movie = new Movie();

        String sql = "SELECT movie_id, title, " +
                "overview, runtime, director, actors, release_date, rating, " +
                "certification, genres FROM movies " +
                "WHERE movie_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, movieId);

        PreparedStatement pstmt = con.prepareStatement("SELECT movie_id, title, " +
                "overview, runtime, director, actors, release_date, rating, " +
                "certification, genres FROM movies " +
                "WHERE movie_id = ?;");

        pstmt.setInt(1, movieId);

        ResultSet rs2 = pstmt.executeQuery();
        if (rs2.next()) {
            Array g = rs2.getArray("genres");
            Integer[] actualGenres = (Integer[]) g.getArray();
            Array a = rs2.getArray("actors");
            String[] actualActors = (String[]) a.getArray();
            if (results.next()) {
                movie = mapRowToMovie(results, actualGenres, actualActors);
            }
        }

        return movie;
    }
        //SELECT * from movies where 20 = ANY(genres);
    public Movie mapRowToMovie(SqlRowSet rs, Integer[] genres, String[] actors) {
        Movie movie = new Movie();
        movie.setMovieId(rs.getInt("movie_id"));
        movie.setTitle(rs.getString("title"));
        movie.setOverview(rs.getString("overview"));
        movie.setRuntime(rs.getInt("runtime"));
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
