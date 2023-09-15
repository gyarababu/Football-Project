package com.football.url.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FootBallMatch {
        private String competition;
        private int year;
        private String team1;
        private String team2;
        private int team1goals;
        private int team2goals;
}
