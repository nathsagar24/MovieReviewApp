package com.example.rockstarmoviereview;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProgrammeDetails {

    //Movie
    @SerializedName("poster_path")
    private String poster_path;
    @SerializedName("backdrop_path")
    private String backdrop_path;
    @SerializedName(value="original_title",alternate={"original_name"})
    private String programmeName;
    @SerializedName("crew")
    private List<Crew> crewList;
    @SerializedName("release_date")
    private String release_date;
    @SerializedName("runtime")
    private int runtime;
    @SerializedName("vote_average")
    private float vote_average;
    @SerializedName("budget")
    private int budget;
    @SerializedName("revenue")
    private int revenue;
    @SerializedName("genres")
    private List<Genre> genres;
    @SerializedName("overview")
    private String overview;
    @SerializedName("results")
    private List<ReviewResult> reviewResults;
    @SerializedName("results")
    private List<ProgrammeVideo> programmeVideos;
    @SerializedName("cast")
    private List<Cast> casts;
    @SerializedName("results")
    private List<SimilarProgramme> similarProgrammes;

    //TV
    @SerializedName("created_by")
    private List<Creator> creators;
    @SerializedName("networks")
    private List<Network> networks;
    @SerializedName("next_episode_to_air")
    private String next_episode;
    @SerializedName("episode_runtime")
    private List<Integer> episodeRuntimes;

    public static class Cast{
        @SerializedName("cast_id")
        private int cast_id;
        @SerializedName("id")
        private int id;
        @SerializedName("credit_id")
        private String credit_id;
        @SerializedName("character")
        private String character;
        @SerializedName("name")
        private String castName;
        @SerializedName("profile_path")
        private String profile_path;

        public int getCast_id() {
            return cast_id;
        }

        public void setCast_id(int cast_id) {
            this.cast_id = cast_id;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCredit_id() {
            return credit_id;
        }

        public void setCredit_id(String credit_id) {
            this.credit_id = credit_id;
        }

        public String getCharacter() {
            return character;
        }

        public void setCharacter(String character) {
            this.character = character;
        }

        public String getCastName() {
            return castName;
        }

        public void setCastName(String castName) {
            this.castName = castName;
        }

        public String getProfile_path() {
            return profile_path;
        }

        public void setProfile_path(String profile_path) {
            this.profile_path = profile_path;
        }
    }

    public static class Crew {
        @SerializedName("department")
        private String department;
        @SerializedName("name")
        private String crewName;

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }

        public String getCrewName() {
            return crewName;
        }

        public void setCrewName(String crewName) {
            this.crewName = crewName;
        }
    }

    public static class Genre {
        @SerializedName("name")
        private String genre;

        public String getGenre() {
            return genre;
        }

        public void setGenre(String genre) {
            this.genre = genre;
        }
    }

    public static class ReviewResult{
        @SerializedName("author")
        private String author;
        @SerializedName("content")
        private String content;

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

    public static class ProgrammeVideo{
        @SerializedName("key")
        private String videoKey;
        @SerializedName("type")
        private String type;

        public String getVideoKey() {
            return videoKey;
        }

        public void setVideoKey(String videoKey) {
            this.videoKey = videoKey;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    public static class SimilarProgramme{
        @SerializedName(value = "original_title",alternate = {"original_name"})
        private String programmeName;
        @SerializedName("backdrop_path")
        private String backdrop_path;

        public String getProgrammeName() {
            return programmeName;
        }

        public void setProgrammeName(String programmeName) {
            this.programmeName = programmeName;
        }

        public String getBackdrop_path() {
            return backdrop_path;
        }

        public void setBackdrop_path(String backdrop_path) {
            this.backdrop_path = backdrop_path;
        }
    }

    public static class Creator{
        @SerializedName("name")
        private String creatorName;

        public String getCreatorName() {
            return creatorName;
        }

        public void setCreatorName(String creatorName) {
            this.creatorName = creatorName;
        }
    }

    public static class Network{
        @SerializedName("name")
        private String networkName;

        public String getNetworkName() {
            return networkName;
        }

        public void setNetworkName(String networkName) {
            this.networkName = networkName;
        }
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getProgrammeName() {
        return programmeName;
    }

    public void setProgrammeName(String programmeName) {
        this.programmeName = programmeName;
    }

    public List<Crew> getCrewList() {
        return crewList;
    }

    public void setCrewList(List<Crew> crewList) {
        this.crewList = crewList;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public float getVote_average() {
        return vote_average;
    }

    public void setVote_average(float vote_average) {
        this.vote_average = vote_average;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public List<ReviewResult> getReviewResults() {
        return reviewResults;
    }

    public void setReviewResults(List<ReviewResult> reviewResults) {
        this.reviewResults = reviewResults;
    }

    public List<ProgrammeVideo> getProgrammeVideos() {
        return programmeVideos;
    }

    public void setProgrammeVideos(List<ProgrammeVideo> programmeVideos) {
        this.programmeVideos = programmeVideos;
    }

    public List<Cast> getCasts() {
        return casts;
    }

    public void setCasts(List<Cast> casts) {
        this.casts = casts;
    }

    public List<SimilarProgramme> getSimilarProgrammes() {
        return similarProgrammes;
    }

    public void setSimilarProgrammes(List<SimilarProgramme> similarProgrammes) {
        this.similarProgrammes = similarProgrammes;
    }

    public List<Creator> getCreators() {
        return creators;
    }

    public void setCreators(List<Creator> creators) {
        this.creators = creators;
    }

    public List<Network> getNetworks() {
        return networks;
    }

    public void setNetworks(List<Network> networks) {
        this.networks = networks;
    }

    public String getNext_episode() {
        return next_episode;
    }

    public void setNext_episode(String next_episode) {
        this.next_episode = next_episode;
    }

    public List<Integer> getEpisodeRuntimes() {
        return episodeRuntimes;
    }

    public void setEpisodeRuntimes(List<Integer> episodeRuntimes) {
        this.episodeRuntimes = episodeRuntimes;
    }
}
