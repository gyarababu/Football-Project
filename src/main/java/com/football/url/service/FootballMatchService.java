package com.football.url.service;

import com.football.url.entity.FootBallMatch;
import com.football.url.payload.FootballMatchList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FootballMatchService {

    Logger logger = LoggerFactory.getLogger(FootballMatchService.class);

    @Async
    public List<FootBallMatch> getMatchesByYear(int year) {
        logger.info("getMatchesByYear method started");
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "https://jsonmock.hackerrank.com/api/football_matches?year=" + year + "&page=1";

        FootballMatchList matchList = restTemplate.getForObject(apiUrl, FootballMatchList.class);

        if (matchList != null) {
            return matchList.getData();
        } else {
            return null;
        }
    }
}


