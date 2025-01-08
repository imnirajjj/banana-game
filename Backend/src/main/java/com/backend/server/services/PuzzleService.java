package com.backend.server.services;

import com.backend.server.model.Puzzle;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PuzzleService {

    private static final String API_URL = "https://marcconrad.com/uob/banana/api.php?out=json";

    private final RestTemplate restTemplate;

    public PuzzleService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Puzzle fetchPuzzle() {
        try {
            String response = restTemplate.getForObject(API_URL, String.class);
            JSONObject jsonObject = new JSONObject(response);

            // Create a new Puzzle object and set its properties
            Puzzle puzzle = new Puzzle();
            puzzle.setQuestion(jsonObject.getString("question"));
            puzzle.setSolution(jsonObject.getInt("solution"));
            return puzzle;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
