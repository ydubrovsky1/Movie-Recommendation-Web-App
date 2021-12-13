package com.techelevator.model;

import java.util.List;

public class UserMovie {

    private int userId;
    private int movieId;
    private List<Integer> movieIds;

    public UserMovie(int userId, int movieId) {
        this.userId = userId;
        this.movieId = movieId;
    }

    public UserMovie(int userId, List<Integer> movieIds) {
        this.userId = userId;
        this.movieIds = movieIds;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public List<Integer> getMovieIds() {
        return movieIds;
    }

    public void setMovieIds(List<Integer> movieIds) {
        this.movieIds = movieIds;
    }
}
