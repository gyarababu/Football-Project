package com.football.url.payload;

import com.football.url.entity.FootBallMatch;

import java.util.List;

public class FootballMatchList {
    private List<FootBallMatch> data;

    public List<FootBallMatch> getData() {
        return data;
    }

    public void setData(List<FootBallMatch> data) {
        this.data = data;
    }
}

