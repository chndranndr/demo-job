package com.example.job.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;

@RestController
public class JobController {

    @GetMapping("/jobs")
    public ResponseEntity<?> getJobList(@RequestHeader("Authorization") String token) {
        // Verify and extract the username from the JWT token
        String username = extractUsernameFromToken(token);

        if (username != null) {
            // Make an HTTP GET request to the provided URL
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.exchange(
                    "http://dev3.dansmultipro.co.id/api/recruitment/positions.json",
                    HttpMethod.GET,
                    null,
                    String.class
            );

            return ResponseEntity.ok(response.getBody());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }
    }

    @GetMapping("/jobs/{id}")
    public ResponseEntity<?> getJobDetail(@RequestHeader("Authorization") String token, @PathVariable("id") String id) {
        String username = extractUsernameFromToken(token);

        if (username != null) {
            RestTemplate restTemplate = new RestTemplate();
            String url = "http://dev3.dansmultipro.co.id/api/recruitment/positions/" + id;
            ResponseEntity<String> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    String.class
            );

            return ResponseEntity.ok(response.getBody());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }
    }

    private String extractUsernameFromToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey("jfJxzlzLVLkkgZggJx8zw7kSsxI6aG83Q2LgmGZJvnc=") // TODO secure key
                    .parseClaimsJws(token.replace("Bearer ", ""))
                    .getBody();

            return claims.getSubject();
        } catch (Exception e) {
            return null;
        }
    }
}
