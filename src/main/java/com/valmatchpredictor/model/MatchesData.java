package com.valmatchpredictor.model;

import java.util.List;

public class MatchesData {
    private int status;
    private List<Match> segments;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Match> getSegments() {
        return segments;
    }

    public void setSegments(List<Match> segments) {
        this.segments = segments;
    }

    public MatchesData(int status, List<Match> segments) {
        this.status = status;
        this.segments = segments;
    }

    public MatchesData() {

    }
}
