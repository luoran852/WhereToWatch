package com.example.wheretowatch;

public class MoviePopularResponse {
    int page;
    SearchedMovie[] results;
    int total_results;
    int total_pages;

    public int getPage() {
        return page;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public int getTotal_results() {
        return total_results;
    }

    public SearchedMovie[] getResults() {
        return results;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setResults(SearchedMovie[] results) {
        this.results = results;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }
}
