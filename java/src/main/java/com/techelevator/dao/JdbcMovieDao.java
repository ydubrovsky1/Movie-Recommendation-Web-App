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
        String sql = "SELECT movie_id, title, " +
             "overview, release_date, rating " +
                "FROM movies " +
                "WHERE movie_id = ?;";
       SqlRowSet results = jdbcTemplate.queryForRowSet(sql, movieId);
        if (results.next()) {
            return mapRowToMovie(results);
        }
        return null;
    }

    public List<Movie> getFavoriteMoviesByUser(int userId){
        List<Movie> movies = new ArrayList<>();
        String sql="SELECT m.movie_id, m.title, " +
                "m.overview, m.release_date, m.rating " +
                "FROM movies m JOIN favorites f ON m.movie_id = f.movie_id "+
                "JOIN users u ON f.user_id = u.user_id "+
                "WHERE u.user_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        while (results.next()) {
            movies.add(mapRowToMovie(results));
        }
        return movies;
    }

    public boolean checkIfInFavorites(int userId, int movieId){
        String sql = "SELECT * FROM favorites WHERE user_id = ? AND movie_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId, movieId);
        if(results.next()){
            return true;
        }
        else{
            return false;
        }
    }


//    public Movie findMovieById(int movieId) throws SQLException {
//
//        Movie movie = new Movie();
//
//        String sql = "SELECT movie_id, title, " +
//                "overview, runtime, director, actors, release_date, rating, " +
//                "certification, genres FROM movies " +
//                "WHERE movie_id = ?;";
//        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, movieId);
//
//        PreparedStatement pstmt = con.prepareStatement("SELECT movie_id, title, " +
//                "overview, runtime, director, actors, release_date, rating, " +
//                "certification, genres FROM movies " +
//                "WHERE movie_id = ?;");
//
//        pstmt.setInt(1, movieId);
//
//        ResultSet rs2 = pstmt.executeQuery();
//        if (rs2.next()) {
//            Array g = rs2.getArray("genres");
//            Integer[] actualGenres = (Integer[]) g.getArray();
//            Array a = rs2.getArray("actors");
//            String[] actualActors = (String[]) a.getArray();
//            if (results.next()) {
//                movie = mapRowToMovie(results, actualGenres, actualActors);
//            }
//        }
//
//        return movie;
//    }

    public boolean saveMovie(Movie movie) throws SQLException {
        String sql = "INSERT INTO movies (movie_id, title, overview, runtime, " +
                "director, actors, release_date, rating, certification) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);"; //VALUES (0, '', '', 0, '', '', '', 0, '', '')
        jdbcTemplate.update(sql,movie.getMovieId(), movie.getTitle(),movie.getOverview(),
                movie.getRuntime(), movie.getDirector(), movie.getActors(), movie.getReleaseDate(),
                movie.getRating(), movie.getCertification());

//        PreparedStatement pstmt = con.prepareStatement("INSERT INTO movies (genres) " +
//                "VALUES (?) WHERE movie_id = ?");
//
//        int[] array = new int[movie.getGenres().size()];
//
//        List<Genre> genreToArray = movie.getGenre();
//        for(int i =0; i < genreToArray.size(); i++) {
//            array[i] = genreToArray.get(i).getId();
//        }
//
//        Object[] values = Arrays.stream(array).mapToObj(i -> Integer.valueOf(i)).toArray();
//
//        Array arraySql = con.createArrayOf("int", values);
//        pstmt.setArray(1, arraySql);
//        pstmt.setInt(2, (int) movie.getMovieId());
//
//        pstmt.executeUpdate();

        return true;
    }

    public boolean addMovieToFavorites(int movieId, int userId){
        String sql = "INSERT INTO favorites (user_id, movie_id) VALUES (?, ?);";
        return jdbcTemplate.update(sql, userId, movieId) == 1;
    }

    public boolean addMovieToWatchList(Movie movie, int userId){
        String sql = "INSERT INTO watch_list (user_id, movie_id) VALUES (?, ?);";
        return jdbcTemplate.update(sql, userId, movie.getMovieId()) == 1;
    }


        //SELECT * from movies where 20 = ANY(genres);
//    public Movie mapRowToMovie(SqlRowSet rs, Integer[] genres, String[] actors) {
//        Movie movie = new Movie();
//        movie.setMovieId(rs.getInt("movie_id"));
//        movie.setTitle(rs.getString("title"));
//        movie.setOverview(rs.getString("overview"));
//        movie.setRuntime(rs.getInt("runtime"));
//        if (rs.getString("director") != null) {
//            movie.setDirector(rs.getString("director"));
//        }
//        List<Genre> actualGenres = new ArrayList<>();
//        if (genres != null) {
//            for (int i = 0; i < genres.length; i++) {
//
//                actualGenres.add(new Genre(genres[i]));
//            }
//            movie.setGenre(actualGenres);
//        }
//        if (actors != null) {
//            movie.setActors(List.of(actors));
//        }
//        movie.setReleaseDate(rs.getString("release_date"));
//        movie.setRating(rs.getDouble("rating"));
//        movie.setCertification(rs.getString("certification"));
//
//        return movie;
//    }

    public Movie mapRowToMovie(SqlRowSet rs){
        Movie movie = new Movie();
        movie.setMovieId(rs.getInt("movie_id"));
        movie.setTitle(rs.getString("title"));
        movie.setOverview(rs.getString("overview"));
        movie.setReleaseDate(rs.getString("release_date"));
       movie.setRating(rs.getDouble("rating"));
       return movie;
    }
}
