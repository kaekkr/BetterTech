package dev.senior.bettertech.service;

import dev.senior.bettertech.BetterTechApplication;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class AIService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String OPENAI_API_KEY = BetterTechApplication.dotenv.get("OPENAI_API_KEY");
    private final String OPENAI_API_URL = "https://api.openai.com/v1/completions";

    public String generateFeedback(String submissionContent) {
        String prompt = "Provide detailed feedback for the following submission:\n\n" + submissionContent;

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "text-davinci-003");
        requestBody.put("prompt", prompt);
        requestBody.put("max_tokens", 200);
        requestBody.put("temperature", 0.7);

        Map<String, String> headers = Map.of(
                "Authorization", "Bearer " + OPENAI_API_KEY,
                "Content-Type", "application/json"
        );

        return restTemplate.postForObject(OPENAI_API_URL, Map.of("body", requestBody, "headers", headers), String.class);
    }
}
