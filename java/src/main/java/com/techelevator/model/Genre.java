package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;

public class Genre {
    private int id;
    @JsonProperty("genreName")
    private String genreName;
    public static final HashMap<Integer, String> genreIdName = new HashMap<>();

    //TODO: genere name based on id


    public Genre() {
        setGenres();
    }

    public Genre(int genreId) {
        setGenres();
        this.id = genreId;
        this.genreName = genreIdName.get(genreId);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    private void setGenres() {
        this.genreIdName.put(28,"Action");
        this.genreIdName.put(12,"Adventure");
        this.genreIdName.put(16,"Animation");
        this.genreIdName.put(35,"Comedy");
        this.genreIdName.put(80,"Crime");
        this.genreIdName.put(99,"Documentary");
        this.genreIdName.put(18,"Drama");
        this.genreIdName.put(10751,"Family");
        this.genreIdName.put(14,"Fantasy");
        this.genreIdName.put(36,"History");
        this.genreIdName.put(27,"Horror");
        this.genreIdName.put(10402,"Music");
        this.genreIdName.put(9648,"Mystery");
        this.genreIdName.put(10749,"Romance");
        this.genreIdName.put(878,"Science Fiction");
        this.genreIdName.put(10770,"TV Movie");
        this.genreIdName.put(53,"Thriller");
        this.genreIdName.put(10752,"War");
        this.genreIdName.put(37,"Western");
    }
}
