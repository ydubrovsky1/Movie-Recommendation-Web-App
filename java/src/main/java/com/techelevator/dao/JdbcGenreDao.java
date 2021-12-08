package com.techelevator.dao;

import com.techelevator.model.Genre;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcGenreDao implements GenreDao {

    private JdbcTemplate jdbcTemplate;
    @Override
    public List<Genre> save(int userId, List<Genre> genres) {
        List<Genre> myGenres = new ArrayList<>();
        String sql = "INSERT INTO user_to_genres (user_id, genre_id) VALUES (?, ?)";
        for(Genre genre : genres){
            jdbcTemplate.update(sql, userId, genre.getId());
        }

        return getGenresByUser(userId);
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
