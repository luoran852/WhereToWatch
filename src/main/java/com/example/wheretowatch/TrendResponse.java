package com.example.wheretowatch;

public class TrendResponse {

    private String title;
    private String original_title;
    private String name;
    private String poster_path;
    private String overview;
    private String backdrop_path;
    private String release_date;
    private int id;
    Movie[] results;

    public TrendResponse(String title, String original_title, String name, int id, String
            poster_path, String overview, String backdrop_path, String release_date) {
        this.title = title;
        this.original_title = original_title;
        this.name = name;
        this.id = id;
        this.poster_path = poster_path;
        this.overview = overview;
        this.backdrop_path = backdrop_path;
        this.release_date = release_date;
    }
    public Movie[] getResults ()
    {
        return results;
    }
    public String getTitle() {
        return title;
    }
    public String getOriginal_title() {
        return original_title;
    }
    public int getId()
    {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getPoster_path() {
        return poster_path;
    }
    public String getOverview() {
        return overview;
    }
    public String getBackdrop_path() {
        return backdrop_path;
    }
    public String getRelease_date() {
        return release_date;
    }
}

