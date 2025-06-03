package com.valmatchpredictor.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Team {
    @JsonProperty("team")
    private String name;
    private String rank;
    private String record;
    private String logo;

    public Team() {
    }

    public Team(String name, String rank, String record, String logo) {
        this.name = name;
        this.rank = rank;
        this.record = record;
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
