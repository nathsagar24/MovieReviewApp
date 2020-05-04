package com.example.rockstarmoviereview;

import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListItem {
    private List<Results> results;


    public List<Results> getResults() {
        return results;
    }

    public void setResults(List<Results> results) {
        this.results = results;
    }

    public static class Results{

        @SerializedName(value="original_title", alternate={"original_name"})
        private String movieName;
        private String backdrop_path;
        private int id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMovieName() {
            return movieName;
        }

        public void setMovieName(String movieName) {
            this.movieName = movieName;
        }

        public String getBackdrop_path() {
            return backdrop_path;
        }

        public void setBackdrop_path(String backdrop_path) {
            this.backdrop_path = backdrop_path;
        }
    }
}
