package com.example.wheretowatch;

public class MovieOTT {
    private Flatrate[] flatrate;
    private Rent[] rent;
    private Buy[] buy;

    public Flatrate[] getFlatrate() {
        return flatrate;
    }

    public Buy[] getBuy() {
        return buy;
    }

    public Rent[] getRent() {
        return rent;
    }

    public void setBuy(Buy[] buy) {
        this.buy = buy;
    }

    public void setFlatrate(Flatrate[] flatrate) {
        this.flatrate = flatrate;
    }

    public void setRent(Rent[] rent) {
        this.rent = rent;
    }
}
class Flatrate {
    private int display_priority;
    private String logo_path;
    private int provider_id;
    private String provider_name;


    // Getter Methods
    public int getDisplay_priority() {
        return display_priority;
    }

    public String getLogo_path() {
        return logo_path;
    }

    public int getProvider_id() {
        return provider_id;
    }

    public String getProvider_name() {
        return provider_name;
    }

    // Setter Methods
    public void setDisplay_priority(int display_priority) {
        this.display_priority = display_priority;
    }

    public void setLogo_path(String logo_path) {
        this.logo_path = logo_path;
    }

    public void setProvider_id(int provider_id) {
        this.provider_id = provider_id;
    }

    public void setProvider_name(String provider_name) {
        this.provider_name = provider_name;
    }
}

class Rent {
    private int display_priority;
    private String logo_path;
    private int provider_id;
    private String provider_name;


    // Getter Methods

    public int getDisplay_priority() {
        return display_priority;
    }

    public String getLogo_path() {
        return logo_path;
    }

    public int getProvider_id() {
        return provider_id;
    }

    public String getProvider_name() {
        return provider_name;
    }

    // Setter Methods


    public void setProvider_id(int provider_id) {
        this.provider_id = provider_id;
    }

    public void setLogo_path(String logo_path) {
        this.logo_path = logo_path;
    }

    public void setDisplay_priority(int display_priority) {
        this.display_priority = display_priority;
    }

    public void setProvider_name(String provider_name) {
        this.provider_name = provider_name;
    }
}

class Buy {
    private int display_priority;
    private String logo_path;
    private int provider_id;
    private String provider_name;


    // Getter Methods


    public int getDisplay_priority() {
        return display_priority;
    }

    public String getLogo_path() {
        return logo_path;
    }

    public int getProvider_id() {
        return provider_id;
    }

    public String getProvider_name() {
        return provider_name;
    }

    // Setter Methods


    public void setDisplay_priority(int display_priority) {
        this.display_priority = display_priority;
    }

    public void setLogo_path(String logo_path) {
        this.logo_path = logo_path;
    }

    public void setProvider_id(int provider_id) {
        this.provider_id = provider_id;
    }

    public void setProvider_name(String provider_name) {
        this.provider_name = provider_name;
    }
}
