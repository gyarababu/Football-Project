package com.football.url.controller;

import com.football.url.entity.FootBallMatch;
import com.football.url.security.JwtTokenProvider;
import com.football.url.service.FootballMatchService;
import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class FootBallController {

    Logger logger = LoggerFactory.getLogger(FootBallController.class);

    private JwtTokenProvider jwtTokenProvider;
    private FootballMatchService footballMatchService;

    public FootBallController(JwtTokenProvider jwtTokenProvider, FootballMatchService footballMatchService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.footballMatchService = footballMatchService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/token-details")
    public ResponseEntity<?> getTokenDetails(@RequestHeader("Authorization") String tokenHeader) {
        logger.info("starting token-details method in controller");
        if (tokenHeader == null || !tokenHeader.startsWith("Bearer ")) {
            return ResponseEntity.badRequest().body("Invalid token format");
        }
        String jwtToken = tokenHeader.substring(7);

        try {
            String username = jwtTokenProvider.getUserName(jwtToken);
            Date expirationDate = jwtTokenProvider.getExpirationDate(jwtToken);

            Map<String, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("expirationDate", expirationDate);

            return ResponseEntity.ok(response);
        } catch (ExpiredJwtException e) {
            logger.error("Exception in token-details unauthorized");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token has expired");
        } catch (Exception e) {
            logger.error("Error occured in token-details");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/draw-matches")
    public ResponseEntity<?> getDrawMatches(
            @RequestHeader("Authorization") String tokenHeader,
            @RequestParam int year) {
        logger.info("starting draw-matches method in controller");
        if (tokenHeader == null || !tokenHeader.startsWith("Bearer ")) {
            return ResponseEntity.badRequest().body("Invalid token format");
        }

        String jwtToken = tokenHeader.substring(7);

        try {
            if (!jwtTokenProvider.validateToken(jwtToken)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token is not valid");
            }

            List<FootBallMatch> matches = footballMatchService.getMatchesByYear(year);

            long drawnMatchesCount = matches.stream()
                    .filter(match -> match.getTeam1goals() == match.getTeam2goals())
                    .count();

            int minDelay = 3000;
            int maxDelay = 6000;
            int randomDelay = minDelay + (int)(Math.random() * (maxDelay - minDelay + 1));
            Thread.sleep(randomDelay);

            return ResponseEntity.ok("Number of drawn matches in " + year + ": " + drawnMatchesCount);
        } catch (ExpiredJwtException e) {
            logger.error("Exception in drawn-matches unauthorized");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token has expired");
        } catch (Exception e) {
            logger.error("Error occured in drawn-matches");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }
}
