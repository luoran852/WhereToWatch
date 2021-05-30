package com.example.wheretowatch;

public class OTTResponse {
    private int id;
    private Results results;

    public int getId() {
        return id;
    }

    public Results getResult() {
        return results;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setResult(Results results) {
        this.results = results;
    }
}

class Results {
    MovieOTT AR;
    MovieOTT AT;
    MovieOTT AU;
    MovieOTT BE;
    MovieOTT BR;
    MovieOTT CA;
    MovieOTT CH;
    MovieOTT CL;
    MovieOTT CO;
    MovieOTT CZ;
    MovieOTT DE;
    MovieOTT DK;
    MovieOTT EC;
    MovieOTT EE;
    MovieOTT ES;
    MovieOTT FI;
    MovieOTT FR;
    MovieOTT GB;
    MovieOTT GR;
    MovieOTT HU;
    MovieOTT ID;
    MovieOTT IE;
    MovieOTT IN;
    MovieOTT IT;
    MovieOTT JP;
    MovieOTT KR;
    MovieOTT LT;
    MovieOTT LV;
    MovieOTT MX;
    MovieOTT MY;
    MovieOTT NL;
    MovieOTT NO;
    MovieOTT NZ;
    MovieOTT PE;
    MovieOTT PH;
    MovieOTT PL;
    MovieOTT PT;
    MovieOTT RO, RU, SE, SG, TH, TR, US, VE, ZA;

    public MovieOTT getKR() {
        return KR;
    }

    public void setKR(MovieOTT KR) {
        this.KR = KR;
    }
}
