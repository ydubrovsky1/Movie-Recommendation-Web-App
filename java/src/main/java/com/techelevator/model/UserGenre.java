package com.techelevator.model;

import java.util.List;

public class UserGenre {

    private int userId;
    private int genreId;
    private List<Integer> genreIds;

    public UserGenre(int userId, int genreId) {
        this.userId = userId;
        this.genreId = genreId;
    }

    public UserGenre(int userId, List<Integer> genreIds) {
        this.userId = userId;
        this.genreIds = genreIds;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
    }
}
