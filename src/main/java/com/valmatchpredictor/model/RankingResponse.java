package com.valmatchpredictor.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class RankingResponse {

    private int status;

    @JsonProperty("data")
    private List<Team_Old_API> teams;

    public RankingResponse() {
    }

    public RankingResponse(int status, List<Team_Old_API> teams) {
        this.status = status;
        this.teams = teams;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Team_Old_API> getTeams() {
        return teams;
    }

    public void setTeams(List<Team_Old_API> teams) {
        this.teams = teams;
    }
}
