package dev.senior.bettertech.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class AIService {
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${OPEN_AI_KEY}")
    private String openAIKey;

    public String generateFeedback(String submissionContent) {
        String prompt = "Provide detailed feedback for the following submission:\n\n" + submissionContent;

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "text-davinci-003");
        requestBody.put("prompt", prompt);
        requestBody.put("max_tokens", 200);
        requestBody.put("temperature", 0.7);

        Map<String, String> headers = Map.of(
                "Authorization", "Bearer " + openAIKey,
                "Content-Type", "application/json"
        );

        String openAIUrl = "https://api.openai.com/v1/completions";
        return restTemplate.postForObject(openAIUrl, Map.of("body", requestBody, "headers", headers), String.class);
    }
}
