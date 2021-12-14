package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

public class UserGenre {

    private int userId;
    private int genreId;
    private List<String> genreIds;

    /*
    public UserGenre(int userId, int genreId) {
        this.userId = userId;
        this.genreId = genreId;
    }
*/

    public UserGenre(int userId, List<String> genreIds) {
        this.userId = userId;
//        for (String genreId : genreIds) {
//            this.genreIds.add(Integer.parseInt(genreId));
//        }
        this.genreIds = genreIds;
    }

//   public UserGenre(int userId, List<Integer> genreIds) {
//        this.userId = userId;
//        this.genreIds = genreIds;
//    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

  public int getGenreId() {
       return genreId;
    }
//
//    public void setGenreId(int genreId) {
//        this.genreId = genreId;
//    }

    public List<String> getGenreIds() {
        return genreIds;
    }

    public List<Integer> getGenreIdsFromStrings(){
        List<Integer> genresAsInts = new ArrayList<>();
        for (String genreId : this.genreIds) {
           genresAsInts.add(Integer.parseInt(genreId));
        }
        return genresAsInts;
    }


//    public void setGenreIds(List<Integer> genreIds) {
//        this.genreIds = genreIds;
//    }
}

