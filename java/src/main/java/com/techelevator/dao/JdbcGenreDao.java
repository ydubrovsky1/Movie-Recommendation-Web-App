package com.techelevator.dao;

import com.techelevator.model.Genre;
import org.springframework.data.relational.core.sql.In;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class JdbcGenreDao implements GenreDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcGenreDao (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Genre> save(int userId, List<Integer> genreIds) {
        List<Genre> myGenres = new ArrayList<>();

        String sql = "INSERT INTO genres_users (user_id, genre_id) VALUES (?, ?)";
        for(Integer genre : genreIds){
            jdbcTemplate.update(sql, userId, genre);
        }

        myGenres = getGenresByUser(userId);

        return myGenres;
    }

    @Override
    public List<Genre> getGenresByUser(int userId) {
        List<Genre> myGenres = new ArrayList<>();
        //TODO: ADJUST THE QUERIES ONCE HAVE TAbles
        String sql = "SELECT g.genre_name, g.genre_id FROM genres g JOIN user_to_genres ug WHERE userId = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        while(results.next()) {
            Genre genre = mapRowToGenre(results);
            myGenres.add(genre);
        }
        return myGenres;
    }

    private Genre mapRowToGenre(SqlRowSet rs) {
        Genre genre = new Genre();
        genre.setGenreName("name");
        genre.setId(rs.getInt("id"));
        return genre;
    };
}
