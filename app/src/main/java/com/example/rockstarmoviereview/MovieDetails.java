package com.example.rockstarmoviereview;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieDetails {
    @SerializedName("original_title")
    String movie_name;
    String poster_path;
    @SerializedName("genres")
    List<Genres> genreList;
    int budget;
    String original_language;
    String overview;
    int vote_average;

    public static class Genres{
        @SerializedName("name")
        String genre;

        public String getGenre() {
            return genre;
        }

        public void setGenre(String genre) {
            this.genre = genre;
        }
    }

    public String getMovie_name() {
        return movie_name;
    }

    public void setMovie_name(String movie_name) {
        this.movie_name = movie_name;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public List<Genres> getGenreList() {
        return genreList;
    }

    public void setGenreList(List<Genres> genreList) {
        this.genreList = genreList;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public int getVote_average() {
        return vote_average;
    }

    public void setVote_average(int vote_average) {
        this.vote_average = vote_average;
    }
}
