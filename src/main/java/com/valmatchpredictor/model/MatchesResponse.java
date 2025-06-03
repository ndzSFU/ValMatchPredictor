package com.valmatchpredictor.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class MatchesResponse {
    private Data data;

    public Data getData() { return data; }
    public void setData(Data data) { this.data = data; }

    public static class Data {
        private int status;
        private List<Match> segments;

        public int getStatus() { return status; }
        public void setStatus(int status) { this.status = status; }

        public List<Match> getSegments() { return segments; }
        public void setSegments(List<Match> segments) { this.segments = segments; }
    }
}
