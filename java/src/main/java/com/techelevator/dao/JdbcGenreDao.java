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
        List<Genre> passedGenres = mapIntegerToGenres(genreIds);
        List<Genre> myGenres = getGenresByUser(userId);
        List<Integer> myGenresIds = new ArrayList<>();
        for(Genre genre : myGenres){
            myGenresIds.add(genre.getId());
        }
        List<Genre> saveList = new ArrayList<>();

        for (Genre passedGenre : passedGenres) {
            if (!myGenresIds.contains(passedGenre.getId())) { //if its not in the my genres list, add it
                saveList.add(passedGenre);
            }
        }


        String sql = "INSERT INTO genres_users (user_id, genre_id) VALUES (?, ?)";
        for(Genre genre : saveList){
            jdbcTemplate.update(sql, userId, genre.getId());
        }

        myGenres = getGenresByUser(userId);
        return myGenres;
    }

    @Override
    public List<Genre> getGenresByUser(int userId) {
        List<Genre> myGenres = new ArrayList<>();
        //TODO: ADJUST THE QUERIES ONCE HAVE TAbles
        String sql = "SELECT g.genre_name, g.genre_id FROM genres g JOIN genres_users gu ON g.genre_id = gu.genre_id WHERE gu.user_id = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        while(results.next()) {
            Genre genre = mapRowToGenre(results);
            myGenres.add(genre);
        }
        return myGenres;
    }

    public Genre getGenreByUser(int userId, int genreId) {
        Genre genre = new Genre();

        String sql = "SELECT g.genre_name, g.genre_id FROM genres g JOIN genres_users gu ON g.genre_id = gu.genre_id WHERE gu.user_id = ? AND gu.genre_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);

        if (results.next()) {
            genre = mapRowToGenre(results);
        }
        return genre;
    }

    public boolean deleteGenreFromUser(int userId, int genreId) {

        if (getGenresByUser(userId).size() < 1) {
            return false;
        }

        String sql = "DELETE FROM genres_users WHERE genre_id = ? and user_id = ?;";

        return jdbcTemplate.update(sql, genreId, userId) == 1;

    }

    private Genre mapRowToGenre(SqlRowSet rs) {
        Genre genre = new Genre();
        genre.setId(rs.getInt("genre_id"));
        return genre;
    }

    private List<Genre> mapIntegerToGenres(List<Integer> ints) {
        List<Genre> genres = new ArrayList<>();
        for (Integer genreId : ints) {
            genres.add( new Genre(genreId));
        }
        return genres;
    }
}
