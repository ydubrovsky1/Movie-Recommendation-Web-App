package com.techelevator.dao;

import com.techelevator.model.Genre;

import java.util.List;

public interface GenreDao {
    List<Genre> save(int userId, List<Integer> genreIds);
    List<Genre> getGenresByUser(int userId);
}
