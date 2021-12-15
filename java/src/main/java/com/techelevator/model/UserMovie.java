package com.techelevator.model;

import java.util.List;

public class UserMovie {

    private int userId;
    private int movieId;


    public UserMovie(int userId, int movieId) {
        this.userId = userId;
        this.movieId = movieId;
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

}


